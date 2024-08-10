package test;

import dao.impl.DaoH2Veterinario;
import model.Veterinario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceTest {
    static final Logger logger = Logger.getLogger(VeterinarioServiceTest.class);
    private static VeterinarioService veterinarioService = new VeterinarioService(new DaoH2Veterinario());

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./Database/veterinarios;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Testear que un veterinario fue cargado correctamente")
    void caso1(){

        //Dado
        Veterinario veterinario = new Veterinario("234","Carlos","Perez","Urologo");
        //cuando
        Veterinario veterinarioDesdeDb = veterinarioService.guardarVeterinario(veterinario);
        // entonces
        assertNotNull(veterinarioDesdeDb);
    }



}
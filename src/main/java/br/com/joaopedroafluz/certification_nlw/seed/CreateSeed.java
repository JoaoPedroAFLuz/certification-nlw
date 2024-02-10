package br.com.joaopedroafluz.certification_nlw.seed;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateSeed {

    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/certification");
        dataSource.setUsername("JoaoPedroAFLuz");
        dataSource.setPassword("docker");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run();
    }

    private void run() {
        executeSqlFile();
    }

    private void executeSqlFile() {
        try {
            String sqlScript = new String(Files.readAllBytes(Path.of("src/main/resources/create.sql")));

            jdbcTemplate.execute(sqlScript);

            System.out.println("Seed realizado com sucesso!");
        } catch (IOException e) {
            System.out.println("Error ao executar arquivo: " + e.getMessage());
        }
    }

}

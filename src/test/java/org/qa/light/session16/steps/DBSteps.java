package org.qa.light.session16.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.qa.light.session10.PersonDto;
import org.qa.light.session10.ResponseDto;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBSteps {

    public static Connection conn;

    public static int dbRowCount;

    @When("I store those people in DB")
    public void iStoreThosePeopleInDB() throws SQLException {
        ResponseDto responseDto = RestSteps.responseDto;
        List<PersonDto> personDtos = responseDto.getResults();
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES (?,?,?,?,?)"
        );

        for (PersonDto personDto : personDtos) {
            preparedStatement.setString(1, personDto.getName().getFirst());
            preparedStatement.setString(2, personDto.getName().getLast());
            preparedStatement.setString(3, personDto.getGender());
            preparedStatement.setString(4, personDto.getName().getTitle());
            preparedStatement.setString(5, personDto.getNat());
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                dbRowCount--;
                System.out.println("Failed to save in DB: " + personDto);
            }
        }
    }

    @Then("Then DB has {int} more entries")
    public void thenDBHas3MoreEntries(int amount) throws SQLException {
        PreparedStatement preparedStatement =
                conn.prepareStatement("SELECT COUNT(*) FROM Persons");
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            Assert.assertEquals(
                    rs.getInt(1),
                    dbRowCount + amount,
                    "DB Entries count mismatch!");
        }
    }

    @Given("Check database row count")
    public void checkDatabaseRowCount() throws SQLException {
        PreparedStatement preparedStatement =
                conn.prepareStatement("SELECT COUNT(*) FROM Persons");
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            dbRowCount = rs.getInt(1);
        }
    }
}

package org.qa.light.session16.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.qa.light.session10.PersonDto;
import org.qa.light.session10.ResponseDto;
import org.qa.light.session12.DataHoler;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBStepsV2 {

    public static Connection conn;

    @When("I store {string} in DB and new entries count is {string}")
    public void iStoreThosePeopleInDB(String alias, String entriesCountAlias) throws SQLException {
        ResponseDto responseDto = (ResponseDto) DataHoler.DATA.get(alias);
        List<PersonDto> personDtos = responseDto.getResults();
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES (?,?,?,?,?)"
        );
        Integer count = 0;
        for (PersonDto personDto : personDtos) {
            preparedStatement.setString(1, personDto.getName().getFirst());
            preparedStatement.setString(2, personDto.getName().getLast());
            preparedStatement.setString(3, personDto.getGender());
            preparedStatement.setString(4, personDto.getName().getTitle());
            preparedStatement.setString(5, personDto.getNat());
            try {
                preparedStatement.execute();
                count++;
            } catch (SQLException e) {
                System.out.println("Failed to save in DB: " + personDto);
            }
        }
        DataHoler.DATA.put(entriesCountAlias, count);
    }

    @Then("DB {string} has {string} more rows")
    public void thenDBHas3MoreEntries(String oldEntriesAlias, String newEntriesCountAlias) throws SQLException {
        PreparedStatement preparedStatement =
                conn.prepareStatement("SELECT COUNT(*) FROM Persons");
        ResultSet rs = preparedStatement.executeQuery();
        int initialRowsCount = (Integer) DataHoler.DATA.get(oldEntriesAlias);
        int newRowsCount = (Integer) DataHoler.DATA.get(newEntriesCountAlias);

        if (rs.next()) {
            Assert.assertEquals(
                    rs.getInt(1),
                    initialRowsCount + newRowsCount,
                    "DB Entries count mismatch!");
        }
    }

    @Given("I store database row count as {string}")
    public void checkDatabaseRowCount(String alias) throws SQLException {
        PreparedStatement preparedStatement =
                conn.prepareStatement("SELECT COUNT(*) FROM Persons");
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            DataHoler.DATA.put(alias, rs.getInt(1));
        }
    }
}

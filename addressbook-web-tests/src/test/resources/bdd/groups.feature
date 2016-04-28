Feature: Groups

  Scenario Outline: Group creation
    Given a set of groups
    When I create a new group with name <name>, header <header> and footer <footer>
    Then the new set of groups consists of the old set and the newly created group

    Examples:
      | name       | header       | footer       |
      | test name1 | test header1 | test footer1 |
      | test name2 | test header2 | test footer2 |
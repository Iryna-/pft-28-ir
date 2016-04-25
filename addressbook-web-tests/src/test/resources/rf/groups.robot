***Settings***
Library    ru.stqa.pft.addressbook.rf.AddressbookKeywords
Suite Setup    Init Application Manager
Suite Teardown    Stop Application Manager


*** Test Cases ***
Can create a group with valid data
    ${old_count} =     Get Group Count
    Create Group    test name    test header    test footer
    ${new_count} =     Get Group Count
    ${expected_count} =    Evaluate    ${old_count} + 1
    Should Be Equal As IntegerS    ${new_count}    ${expected_count}

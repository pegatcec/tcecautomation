Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: IAC User - Tertiary Application
Given I am a <entitlementType>
When I submit the cec application with <uniName> <concessionID>
And I submit personal details with <firstName> <lastName> <emailID> <phone> <mobile>
And I submit residential details with <addressLine1> <suburb><postCode>
Then I should see the outcome message



Examples:
entitlementType      | uniName           |   concessionID   |firstName|lastName|emailID      |phone     |mobile    |addressLine1|suburb    |postCode|
Tertiary student     | University of NSW |   123456789      |Test     |Test    |test@test.com|0288358835|0411111111|53, High St |Parramatta| 2150   |


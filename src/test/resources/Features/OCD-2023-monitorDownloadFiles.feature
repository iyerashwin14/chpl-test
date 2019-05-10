@Regression 
@downloadFiles
Feature: Monitor that download files and definition files on CHPL download resources page are correct and current
       OCD-2023: Monitor that download files on CHPL download resources page are correct and current 
       Verify download files are recent, to ensure file generation didn't fail at any point.
       OCD-2823: Check that definition files exist on overnight checks 

  Background: 
    We need to start with an empty download directory, so that we can be sure we're checking the right file for recency. 
    Given I am on download the CHPL resources page on "<env>" 
    And the download directory is empty 

  Scenario Outline: The download files are "recent" 
    Given I am on download the CHPL resources page on "<env>" 
    When I download the "<edition>" "<type>" products file 
    Then the download file is no more than "<days>" days old 
    And the download file is at least "<size>" "<units>" in size 
    And the download file has at least "<items>" items 
    Examples: 
      | env  | edition | type | days | size | units | items |
      | DEV  |    2011 | xml  |   92 |  148 | MB    |  3885 |
      | DEV  |    2014 | xml  |    1 |  470 | MB    |  5250 |
      | DEV  |    2014 | csv  |    1 | 2900 | KB    |  5250 |
      | DEV  |    2015 | xml  |    1 |  180 | MB    |   710 |
      | DEV  |    2015 | csv  |    1 |  420 | KB    |   710 |
      | STG  |    2011 | xml  |   92 |  148 | MB    |  3885 |
      | STG  |    2014 | xml  |    1 |  470 | MB    |  5250 |
      | STG  |    2014 | csv  |    1 | 2900 | KB    |  5250 |
      | STG  |    2015 | xml  |    1 |  180 | MB    |   710 |
      | STG  |    2015 | csv  |    1 |  420 | KB    |   710 |
      | PROD |    2011 | xml  |   92 |  148 | MB    |  3885 |
      | PROD |    2014 | xml  |    1 |  470 | MB    |  5250 |
      | PROD |    2014 | csv  |    1 | 2900 | KB    |  5250 |
      | PROD |    2015 | xml  |    1 |  180 | MB    |   710 |
      | PROD |    2015 | csv  |    1 |  420 | KB    |   710 |

  Scenario Outline: 
    At any given time, when downloaded, file name ends with Date and time format 
    Given I am on download the CHPL resources page on "<env>" 
    When I select the "<File>", download it from drop-down and I see the "<Downloaded File Name>" 
    Then the "<Downloaded File Name>" ends with "yyyyMMdd_HHmmss" and is no more than Days "<Days>" old 
    Examples: 
      | env  | File                 |Downloaded File Name             |Days|
      | DEV  | Surveillance Activity|surveillance-all                 |1   |
      | DEV  | Non-Conformities     |surveillance-with-nonconformities|1   |
      | STG  | Surveillance Activity|surveillance-all                 |1   |
      | STG  | Non-Conformities     |surveillance-with-nonconformities|1   |
      | PROD | Surveillance Activity|surveillance-all                 |1   |
      | PROD | Non-Conformities     |surveillance-with-nonconformities|1   |

  Scenario Outline: 
    At any given time, when downloaded, SED Details file name ends with Date and time format 
    Given I am on collections page: "<Page Name>" on "<env>" 
    When I download the "<File Name>" file 
    Then the "<File Name>" ends with "yyyyMMdd_HHmmss" and is no more than Days "<Days>" old 
    Examples: 
      | env  | Page Name| File Name |Days|
      | DEV  | sed      | chpl-sed  |1   |
      | STG  | sed      | chpl-sed  |1   |
      | PROD | sed      | chpl-sed  |1   |
  
  Scenario Outline: 
    At any given time, for a non logged in user, 7 definition files should be available to download
    Given I am on download the CHPL resources page on "<env>"
    When I select the definition "<File>", download it from drop-down and I see the "<Downloaded File Name>"
    And the download file is at least "<size>" "<units>" in size
    Examples:
    | env  | File                                       | Downloaded File Name                                  | size| units|
    | DEV  | 2015 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | DEV  | 2014 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | DEV  | 2011 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | DEV  | 2015 edition summary (csv) Definition File | 2015 Listing CSV Data Dictionary.csv                  |  7  | KB   |
    | DEV  | 2014 edition summary (csv) Definition File | 2014 Listing CSV Data Dictionary.csv                  |  8  | KB   |
    | DEV  | Surveillance Activity Definition File      | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | DEV  | Non-Conformities Definition File           | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | STG  | 2015 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | STG  | 2014 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | STG  | 2011 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | STG  | 2015 edition summary (csv) Definition File | 2015 Listing CSV Data Dictionary.csv                  |  7  | KB   |
    | STG  | 2014 edition summary (csv) Definition File | 2014 Listing CSV Data Dictionary.csv                  |  8  | KB   |
    | STG  | Surveillance Activity Definition File      | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | STG  | Non-Conformities Definition File           | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | PROD | 2015 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | PROD | 2014 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | PROD | 2011 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | PROD | 2015 edition summary (csv) Definition File | 2015 Listing CSV Data Dictionary.csv                  |  7  | KB   |
    | PROD | 2014 edition summary (csv) Definition File | 2014 Listing CSV Data Dictionary.csv                  |  8  | KB   |
    | PROD | Surveillance Activity Definition File      | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | PROD | Non-Conformities Definition File           | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    
  Scenario Outline: 
    At any given time, for a logged in admin user, 8 definition files should be available to download
    Given I'm logged in as "ROLE_ADMIN" on "<env>"
    And I am on download the CHPL resources page on "<env>"
    When I select the definition "<File>", download it from drop-down and I see the "<Downloaded File Name>"
    And the download file is at least "<size>" "<units>" in size
    Examples:
    | env  | File                                       | Downloaded File Name                                  | size| units|
    | DEV  | 2015 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | DEV  | 2014 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | DEV  | 2011 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | DEV  | 2015 edition summary (csv) Definition File | 2015 Listing CSV Data Dictionary.csv                  |  7  | KB   |
    | DEV  | 2014 edition summary (csv) Definition File | 2014 Listing CSV Data Dictionary.csv                  |  8  | KB   |
    | DEV  | Surveillance Activity Definition File      | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | DEV  | Surveillance (Basic) Definition File       | Surveillance (basic) CSV Data Dictionary.csv          |  5  | KB   |
    | DEV  | Non-Conformities Definition File           | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | STG  | 2015 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | STG  | 2014 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | STG  | 2011 edition products (xml) Definition File| listingSchema.xsd                                     |  83 | KB   |
    | STG  | 2015 edition summary (csv) Definition File | 2015 Listing CSV Data Dictionary.csv                  |  7  | KB   |
    | STG  | 2014 edition summary (csv) Definition File | 2014 Listing CSV Data Dictionary.csv                  |  8  | KB   |
    | STG  | Surveillance Activity Definition File      | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    | STG  | Surveillance (Basic) Definition File       | Surveillance (basic) CSV Data Dictionary.csv          |  5  | KB   |
    | STG  | Non-Conformities Definition File           | Surveillance and Nonconformity CSV Data Dictionary.csv|  7  | KB   |
    
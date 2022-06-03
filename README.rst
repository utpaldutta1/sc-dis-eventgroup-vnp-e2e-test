================
eventgroup-vnp
================


E2E Automation tests repository for eventgroup-vnp
----------------------------------------------------


Deploy the codebuild project
-------------------

1. `Install the AWS CLI <https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html>`__
2. Make sure you have your AWS credentials sorted with aws-azure-login
3. Provide admin access to the GitHub Team 'sc-cloud-engineers'
4. Run the command with the AWS_PROFILE set to non production account

    .. code-block:: bash

        make codebuild_project_pr
        make codebuild_project_main
        
    or

    .. code-block:: bash

        make codebuild_projects
        
Setup credentials for Checkmarx scans
--------------------------------------

1. Pre-requisites (Required to be done only once per team)
    1. Request your Infosec Lead for Checkmarx Team Id, UserName and Password
    2. Use ssh-keygen to create a SSDLC key. Share the public key with Infosec Lead
    
2. Setup GitHub secrets and deploy keys (Required for every repository created using cookiecutter)
    1. Use ssh-keygen to create CHECKMARX key
    2. Go to Settings > Secrets > 'New repository secret' and add the following
    
        .. list-table:: 
            :widths: 25 25 

            * - Name
              - Value
            * - CHECKMARX_USER                
              - <UserName>
            * - CHECKMARX_PASSWORD
              - <Password>
            * - SSDLC_ACTION                  
              - <SSDLC private key contents>
            * - CHECKMARX_GIT_PRIVATE_KEY     
              - <CHECKMARX private key contents>
    
    3. Go to Settings > Deploy keys > 'Add deploy key' to add a deploy key
    
        .. list-table:: 
            :widths: 25 25 

            * - Title
              - Value
            * - Checkmarx Deploy Key                 
              - <CHECKMARX public key contents>     
            
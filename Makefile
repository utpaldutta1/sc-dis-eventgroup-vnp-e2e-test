##########################################################################################################
#
# eventgroup-vnp
#
##########################################################################################################

ifndef VERBOSE
.SILENT:
endif

SHELL						= /bin/bash
WATCH						= /usr/bin/watch
AWS							= aws
AWS_REGION					= eu-west-1

VENV						?= .venv
VENV_ACTIVATE				=. $(VENV)/bin/activate

CODEBUILD_PRJ_PR_STACK		:=cicd--pr--sc-dis-eventgroup-vnp-e2e-test
CODEBUILD_PRJ_MAIN_STACK	:=cicd--main--sc-dis-eventgroup-vnp-e2e-test

all							: validate

codebuild_projects   	:
							$(AWS) cloudformation deploy --stack-name $(CODEBUILD_PRJ_PR_STACK)	\
                        	--template-file cicd/codebuild_project_pr.yml		\
                        	--parameter-overrides file://cicd/codebuild_project_pr_parameters.json 				\
                        	--capabilities CAPABILITY_IAM;	\
							$(AWS) cloudformation deploy --stack-name $(CODEBUILD_PRJ_MAIN_STACK)	\
                        	--template-file cicd/codebuild_project_main.yml		\
                        	--parameter-overrides file://cicd/codebuild_project_main_parameters.json 			\
                        	--capabilities CAPABILITY_IAM                          	

codebuild_project_pr    	:
							$(AWS) cloudformation deploy --stack-name $(CODEBUILD_PRJ_PR_STACK)	\
                        	--template-file cicd/codebuild_project_pr.yml		\
                        	--parameter-overrides file://cicd/codebuild_project_pr_parameters.json 				\
                        	--capabilities CAPABILITY_IAM
                        	
codebuild_project_main    	:
							$(AWS) cloudformation deploy --stack-name $(CODEBUILD_PRJ_MAIN_STACK)	\
                        	--template-file cicd/codebuild_project_main.yml		\
                        	--parameter-overrides file://cicd/codebuild_project_main_parameters.json 			\
                        	--capabilities CAPABILITY_IAM                        	
                        
validate					:
							$(AWS) cloudformation validate-template \
                        	--template-body file://cicd/codebuild_project_pr.yml;	\
							$(AWS) cloudformation validate-template \
                        	--template-body file://cicd/codebuild_project_main.yml                      	
                        	

events                  	:
							$(AWS) cloudformation describe-stack-events \
							--stack-name $(CODEBUILD_PRJ_PR_STACK) \
							--region $(AWS_REGION)

watch                   	:
							$(WATCH) --interval 1 "bash -c 'make events | head -40'"

.PHONY                  	: all venv venv-install pipeline validate events watch

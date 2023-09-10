# discord-image-sender

## Deploy

### First time

Requirements: An AWS account, node.js / npm installed

1. Clone this repository
2. Install CDK as described in
   the [CDK docs](https://docs.aws.amazon.com/cdk/v2/guide/work-with.html#work-with-prerequisites)
3. Configure authentication with AWS as described in
   the [CDK docs](https://docs.aws.amazon.com/cdk/v2/guide/getting_started.html#getting_started_auth)
4. Bootstrap: `cdk bootstrap aws://ACCOUNT-NUMBER/REGION --profile YOUR-PROFILE-NAME` (PowerUserAccess is not enough
   since it needs to create IAM roles - Administrator Access works)
5. Deploy: Run `cmd /c "cd infrastructure && cdk deploy --profile YOUR-PROFILE-NAME"` in the project root directory

### Later deployements

1. `aws configure sso`. This opens a browser window where you log in using your programmatic access credentials. Back in
   the command line, you can skip all input prompts.
2. `cmd /c "cd infrastructure && cdk deploy --profile PROFILE-NAME"` where PROFILE-NAME is the name of the profile you just configured.

# Discord Image Sender

This is an application that allows you to easily set up a Discord bot that sends images based on a schedule.

You create and provide:

- an AWS account to host the application infrastructure
- a Discord WebHook
- an image link
- a cron expression to schedule when the image will be sent.

This application creates and provides for you:

- the resources and infrastructure to host the bot
- a trigger that automatically triggers the bot based on your schedule
- the bot itself

## Configure

Edit the values in `CONFIG.properties`.

To later change a scheduled image, put in the stack.name value you used to create the scheduled image.
Make changes to the other values as you please and deploy again.

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

1. `aws configure sso`. For "SSO session name" you choose the session you configured during your first deployment. This
   opens a browser window where you log in using your programmatic access credentials. Back in the command line, you can
   skip all input prompts.
2. `cmd /c "cd infrastructure && cdk deploy --profile PROFILE-NAME"` where PROFILE-NAME is the name of the profile you
   just configured.

## Delete

To remove a scheduled image:

- Set stack.name in `CONFIG.properties` to the value you used to create the scheduled image.
- Run `cmd /c "cd infrastructure && cdk destroy STACK-NAME --profile PROFILE-NAME"` where STACK-NAME is your stack.name
  property.

## Cost

I calculated the cost in 2023 using the [AWS pricing calculator](https://calculator.aws/#/addService/Lambda).
The goal of this is to be **free**.

The bot uses EventBridge rules to schedule the images and Lambda to send them.
As of 2023, EventBridge rules are free.
Therefore, the calculation focuses on the Lambda pricing.

You can schedule **up to 213,333 images to be sent each month** and still be within the AWS forever free tier.
With each request (to send an image) terminating after 15s and 128 MB of memory allocated, that results in 393,750
GB-seconds of compute, which is below the free tier limit of 400,000.

- Example 1: You could have 5000 images scheduled to be sent daily. *This would send 155,000 images per month.*
- Example 2: You could have 4 images scheduled to be sent every minute. *This would send 175,320 images per month.*

## Troubleshooting

- I forgot the stack.name value I used to create a scheduled image.
    - Open the AWS Console and navigate to CloudFormation > Stacks. There,
      you will find a list of all deployed Stacks with their names.
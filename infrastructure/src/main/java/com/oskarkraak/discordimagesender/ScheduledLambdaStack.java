package com.oskarkraak.discordimagesender;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.events.targets.LambdaFunction;
import software.amazon.awscdk.services.lambda.*;
import software.amazon.awscdk.services.events.*;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.util.Map;

public class ScheduledLambdaStack extends Stack {
    public ScheduledLambdaStack(final Construct scope, final String id, Code code, String handler, Schedule schedule, Map<String, String> environment) {
        super(scope, id);
        Function lambdaFunction = Function.Builder.create(this, "scheduled-lambda")
                .code(code)
                .handler(handler)
                .timeout(Duration.seconds(15))
                .runtime(Runtime.JAVA_17)
                .environment(environment)
                .build();
        Rule rule = Rule.Builder.create(this, "schedule")
                .schedule(schedule)
                .build();
        rule.addTarget(new LambdaFunction(lambdaFunction));
    }
}

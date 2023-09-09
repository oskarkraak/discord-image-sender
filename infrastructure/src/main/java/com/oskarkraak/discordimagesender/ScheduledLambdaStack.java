package com.oskarkraak.discordimagesender;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.lambda.*;
import software.amazon.awscdk.services.events.*;
import software.constructs.Construct;

public class ScheduledLambdaStack extends Stack {
    public ScheduledLambdaStack(final Construct scope, final String id, Code code, String handler, Schedule schedule) {
        super(scope, id);
        // TODO
    }
}

package com.frandorado.loggingrequestresponsewithbody.bootstrap;

import java.util.UUID;
import java.util.function.Function;

public class Utils {

    public static Function<Void, String> getRandomString = nothing ->
            UUID.randomUUID().toString().replaceAll("-","");
}

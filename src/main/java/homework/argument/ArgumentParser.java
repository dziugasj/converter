package homework.argument;

import homework.argument.Argument;

public class ArgumentParser {
    public Argument getParsedArgument(String[] args) {


        System.out.println(args.length);


        return new Argument(null, null, null);
    }
}

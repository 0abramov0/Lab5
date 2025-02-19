import utils.Invoker;
import loaders.JsonLoader;

public class Main {
    public static void main(String[] args) {
//        if(args.length != 1){
//            return;
//        }
        JsonLoader loader = new JsonLoader("input.json");
        loader.load();

        Invoker invoker = new Invoker();
        invoker.interactive_mode();
    }
}
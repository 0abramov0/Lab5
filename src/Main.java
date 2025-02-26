import loaders.Loader;
import utils.Invoker;
import loaders.JsonLoader;


public class Main {
    public static void main(String[] args) {
//        if(args.length != 1){
//            return;
//        }
        Loader loader = new JsonLoader("input.json");
        loader.load();

        Invoker invoker = new Invoker();
        invoker.interactiveMode();
    }
}
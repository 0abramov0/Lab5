import utils.IO.Console;
import managers.CollectionManager;
import managers.CommandManager;
import utils.loaders.Loader;
import utils.savers.JsonSaver;
import utils.savers.Saver;
import utils.Invoker;
import utils.loaders.JsonLoader;


public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Требуется название файла для загрузки");
            return;
        }
        String filePath = args[0];
//        String filePath = "input.json";
        Loader loader = new JsonLoader(filePath);
        loader.load();

        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        Saver saver = new JsonSaver(filePath, collectionManager);
        Invoker invoker = new Invoker(console, collectionManager, commandManager, saver);
        invoker.interactiveMode();
    }
}
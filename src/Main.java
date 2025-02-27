import IO.Console;
import managers.CollectionManager;
import managers.CommandManager;
import parsers.loaders.Loader;
import parsers.savers.JsonSaver;
import parsers.savers.Saver;
import utils.Invoker;
import parsers.loaders.JsonLoader;


public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Требуется название файла для загрузки");
            return;
        }
        Loader loader = new JsonLoader(args[0]);
        loader.load();

        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        Saver saver = new JsonSaver(args[0]);
        Invoker invoker = new Invoker(console, collectionManager, commandManager, saver);
        invoker.interactiveMode();
    }
}
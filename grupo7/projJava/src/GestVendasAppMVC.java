import Models.*;
import View.*;
import Controller.*;
import Utils.*;

import java.nio.file.*;

public class GestVendasAppMVC
{
    public static void main(String[] args)
    {
        IGestVendas gestVendas = new GestVendas();
        MVC_View_MainMenu view = new MVC_View_MainMenu();
        MVC_Controller controlador = new MVC_Controller();

        Path cfg_path = Paths.get(System.getProperty("user.dir") + "/config.cfg");
        if (!Files.isReadable(cfg_path)) System.exit(0);
        StreamReader sr_config = new StreamReader(cfg_path.toString());
        sr_config.parseConfig(gestVendas);

        controlador.setModel(gestVendas);
        controlador.setView(view);
        controlador.start();

    }
}

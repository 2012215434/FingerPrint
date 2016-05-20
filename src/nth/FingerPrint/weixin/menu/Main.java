package nth.FingerPrint.weixin.menu;

import org.apache.commons.logging.LogFactory;
/**
 * Created by lanqx on 2014/12/8.
 */
public class Main {
    public static void main(final String[] args) {
        Menu menu=new Menu();
        System.out.println(menu.createMenu());
        System.out.println(menu.getMenu());
    }
}

package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO
 *
 * @author Zbq
 * @since 2019/10/29 21:48
 */
public class Test {

        public static void main(String[] args) {
            List<String> list = new ArrayList<String>();
            list.add("公众号");
            list.add("why技术");
//        System.out.println("before remove list = " + list);
            Integer loopTime =1;
            Iterator var3 = list.iterator();
            while(var3.hasNext()) {
                System.out.println("loopTime = " + loopTime);
                loopTime = loopTime + 1;
                String item = (String)var3.next();
                /*if ("why技术".equals(item)) {
                    list.remove(item);
                }*/
            }
//        System.out.println("after remove list = " + list);
        }

}

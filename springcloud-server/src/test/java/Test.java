import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;

import com.sapling.common.tools.common.StringUtil;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/22
 * @since v1.0
 */
public class Test {

    public static void main(String[] args) {
        EurekaClientConfigBean clientBean = new EurekaClientConfigBean();
        Class client = EurekaClientConfigBean.class;
        Class instance = EurekaInstanceConfigBean.class;
//        System.out.println("<table>\n" +
//                "\t<hr>\n" +
//                "  \t<td width=\"25%\">properties</td>\n" +
//                "  \t<td width=\"25%\">yml</td>\n" +
//                "  \t<td width=\"25%\">说明</td>\n" +
//                "  \t<td width=\"25%\">默认值</td>\n" +
//                "  </hr>");
//        Arrays.stream(client.getDeclaredFields()).filter(f->{
//            return Modifier.isPrivate(f.getModifiers());
//        }).forEach(f->{
//            String value = "<tr>\n" +
//                    "  \t<td width=\"25%\">" + f.getName() + "</td>\n" +
//                    "  \t<td width=\"25%\">"+ StringUtil.toUnderLine(f.getName()) + "</td>\n" +
//                    "  \t<td width=\"25%\"></td>\n" +
//                    "  \t<td width=\"25%\"></td>\n" +
//                    "  </tr>";
//            System.out.println(value);
//        });
        System.out.println("==============eureka.client==============");
//        Arrays.stream(instance.getDeclaredFields()).forEach(f->System.out.println("eureka.instance." + f.getName()));
        Arrays.stream(client.getDeclaredFields()).filter(f->{
            return Modifier.isPrivate(f.getModifiers());
        }).forEach(f->{
            f.setAccessible(true);
            System.out.println("### " + f.getName());
            System.out.println("- yml属性值为：" + StringUtil.toUnderLine(f.getName()));
            System.out.println("- 类型：" + f.getType().getName());
            try {
                System.out.println("- 默认值：" + f.get(clientBean));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("- 说明：" );


        });

    }
}

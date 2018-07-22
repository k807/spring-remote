package per.wph;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 22:23
 * =============================================
 */
public class OutputServiceImpl implements OutputService {

    @Override
    public void output(String text){
        System.out.println(text);
    }

}

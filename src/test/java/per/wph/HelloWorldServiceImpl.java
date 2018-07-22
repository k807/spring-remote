package per.wph;

import per.wph.HelloWorldService;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 22:21
 * =============================================
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputService outputService;

    @Override
    public void helloWorld(){
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

}

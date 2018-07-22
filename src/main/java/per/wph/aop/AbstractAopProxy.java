package per.wph.aop;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 21:30
 * =============================================
 */
public abstract class AbstractAopProxy implements AopProxy {
    protected AdviceSupport adviceSupport;

    public AbstractAopProxy(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

}

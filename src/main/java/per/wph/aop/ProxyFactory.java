package per.wph.aop;

public class ProxyFactory extends AdviceSupport implements AopProxy {
    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    public AopProxy createAopProxy(){
        return new Cglib2AopProxy(this);
    }
}

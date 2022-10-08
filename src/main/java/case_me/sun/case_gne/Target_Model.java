package case_me.sun.case_gne;

import com.android.dx.*;
import msun.utils.FileUtils;

import java.lang.reflect.Modifier;

public class Target_Model {

    /**
     * general Method
     */
    public static class Model {
        // auto general  method
        public void xxx() {
            System.out.println(this);
        }

    }

    public static void main(String[] args) {
        new Model().xxx();
        try {
            Target_Model.generate();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void generate() throws Throwable {
        DexMaker dexMaker = new DexMaker();

        TypeId<?> modelTid = TypeId.get("LModel;");
//        TypeId<?> bTid = TypeId.get("LB;");

        dexMaker.declare(modelTid, "Model.generated", Modifier.PUBLIC, TypeId.OBJECT);
//        dexMaker.declare(bTid, "B.generated", 0, TypeId.OBJECT);

        MethodId<Object, Void> objectInitMid = TypeId.OBJECT.getConstructor();
        MethodId<?, Void> modelInitMid = modelTid.getConstructor();
//        MethodId<?, Void> bInitMid = bTid.getConstructor();
        MethodId<?, Void> xxxMid = modelTid.getMethod(TypeId.VOID, "xxx");
//        MethodId<?, Void> aMid = bTid.getMethod(TypeId.VOID, "a");

        Code modelInitCode = dexMaker.declare(modelInitMid, 0);
        Local<?> modelInitThisLocal = modelInitCode.getThis(modelTid);
        modelInitCode.invokeDirect(objectInitMid, null, modelInitThisLocal);
        modelInitCode.returnVoid();

//        Code bInitCode = dexMaker.declare(bInitMid, 0);
//        Local<?> bInitThisLocal = bInitCode.getThis(bTid);
//        bInitCode.invokeDirect(objectInitMid, null, bInitThisLocal);
//        bInitCode.returnVoid();

        Code xxxCode = dexMaker.declare(xxxMid, 0);

        xxxCode.getThis(modelTid);
        xxxCode.returnVoid();


//        Code aCode = dexMaker.declare(aMid, 0);
//        Local mLocal = aCode.newLocal(modelTid);
//        aCode.newInstance(mLocal, modelInitMid);
//        aCode.invokeVirtual(xxxMid, null, mLocal);
//        aCode.returnVoid();

        byte[] dexBytes = dexMaker.generate();
        FileUtils.write("test.dex", dexBytes);
    }
}

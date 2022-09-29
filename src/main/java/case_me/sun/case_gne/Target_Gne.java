package case_me.sun.case_gne;

import com.android.dx.*;
import msun.utils.FileUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Target_Gne {

    /**
     * 生成的目标类。 类非静态，因内部类，所以此处使用类声明用static
     */
    public static class Gne {
        public static String getName(String base) throws NoSuchMethodException {
            java.lang.reflect.Method method = Gne.class.getDeclaredMethod("getName", String.class);
            android.util.Log.i("sanbo.demo", method.toGenericString());
            return null;
        }
    }

    /**
     * 实际生成dex的方法
     * @throws Exception
     */
    public static void generate() throws Exception {
        String targetClassName = "Gne";
        DexMaker dexMaker = new DexMaker();
        // 定义类名,并生成类
        TypeId<?> classNameTypeId = TypeId.get("L" + targetClassName + ";");
        dexMaker.declare(classNameTypeId, targetClassName + ".java", Modifier.PUBLIC, TypeId.OBJECT);

        //define error
        // TypeId<NoSuchMethodException> errTypeId = TypeId.get(NoSuchMethodException.class);
        TypeId<Class> classTypeId = TypeId.get(Class.class);
        TypeId<Class[]> classesTypeId = TypeId.get(Class[].class);
        TypeId<Method> methodTypeId = TypeId.get(Method.class);

        MethodId<?, String> methodId = classNameTypeId.getMethod(TypeId.STRING, "getName", TypeId.STRING);
        MethodId<Class, Class> forNameMid = classTypeId.getMethod(classTypeId, "forName", TypeId.STRING);
        Code code = dexMaker.declare(methodId, Modifier.PUBLIC);
        // Local<String> argsOneLacle = code.getParameter(0, TypeId.STRING);
        // Local<String> result = code.newLocal(TypeId.STRING);

        //define getDeclaredMethod
        Local<String> methodNameLocal = code.newLocal(TypeId.STRING);                       // arg1
        // Local<Class[]> classArgsTypeLocal = code.newLocal(TypeId.get(Class[].class));       // arg2
        Local<Method> getMethodResultLocal = code.newLocal(TypeId.get(Method.class));       // return

        // define toGenericString
        Local<String> toGenralStringResultLocal = code.newLocal(TypeId.STRING);        // arg1

        // define log
        Local<String> logArg1Local = code.newLocal(TypeId.STRING);        // arg1
        // Local<String> logArg2Local = code.newLocal(TypeId.STRING);        // arg2
        // Local<Integer> logResultLocal = code.newLocal(TypeId.INT);       // return
        Local<String> classNameLocal = code.newLocal(TypeId.STRING);
        Local<Class> classLocal = code.newLocal(classTypeId);
        Local<Integer> parameterCountLocal = code.newLocal(TypeId.INT);
        Local<Class[]> parameterTypesLocal = code.newLocal(TypeId.get(Class[].class));
        Local<String> stringNameLocal = code.newLocal(TypeId.STRING);
        Local<Class> stringClassLocal = code.newLocal(classTypeId);
        Local<Integer> zeroLocal = code.newLocal(TypeId.INT);
        Local<String> returnValueLocal = code.newLocal(TypeId.STRING);

        //call getDeclaredMethod
        MethodId getMethod = classTypeId.getMethod(TypeId.get(Method.class), "getDeclaredMethod", TypeId.STRING, classesTypeId);
        code.loadConstant(classNameLocal, targetClassName);
        // 暂不支持 const-class, 只能这样了
        code.invokeStatic(forNameMid, classLocal, classNameLocal);
        code.loadConstant(methodNameLocal, "getName");
        code.loadConstant(parameterCountLocal, 1);
        code.newArray(parameterTypesLocal, parameterCountLocal);
        code.loadConstant(zeroLocal, 0);
        code.loadConstant(stringNameLocal, "java.lang.String");
        code.invokeStatic(forNameMid, stringClassLocal, stringNameLocal);
        code.aput(parameterTypesLocal, zeroLocal, stringClassLocal);
        code.invokeVirtual(getMethod, getMethodResultLocal, classLocal, methodNameLocal, parameterTypesLocal);

        // code.loadConstant(classArgsTypeLocal, new Class[]{String.class});
        // code.invokeStatic(getMethod, getMethodResultLocal, methodNameLocal, classArgsTypeLocal);

        //method.toGenericString()
        MethodId toGenericString = methodTypeId.getMethod(TypeId.STRING, "toGenericString");
        code.invokeVirtual(toGenericString, toGenralStringResultLocal, getMethodResultLocal);

        //log
        MethodId log = TypeId.get(android.util.Log.class).getMethod(TypeId.INT, "i", TypeId.STRING, TypeId.STRING);
        code.loadConstant(logArg1Local, "sanbo.demo");
        // code.loadConstant(logArg2Local,toGenralStringResultLocal.toString());
        code.invokeStatic(log, null, logArg1Local, toGenralStringResultLocal);

        // throws NoSuchMethodException
        //Local errLocal = code.newLocal(errTypeId);
        //code.throwValue(errLocal);
        code.loadConstant(returnValueLocal, null);
        code.returnValue(returnValueLocal);


        /**
         * import android.util.Log;
         *
         * public class Gne {
         *     public String getName(String arg13) {
         *         Log.i("sanbo.demo", Gne.class.getDeclaredMethod("getName", String.class).toGenericString());
         *         return null;
         *     }
         * }
         */
        FileUtils.write("test.dex", dexMaker.generate());
        // ClassLoader cl = dexMaker.generateAndLoad(getClass().getClassLoader(), mContext.getCacheDir());
        // EL.i("classLoader:" + cl);


//        File f =getDataDirectory();
//        Log.i("sanbo","f:" + f.getAbsolutePath());
//        ClassLoader cl = dexMaker.generateAndLoad(getClass().getClassLoader(),f );
//        Log.i("sanbo","classLoader:" + cl);
//        cl.loadClass("Gne").getMethod("getName",String.class).invoke(null,"xxx");
    }
}

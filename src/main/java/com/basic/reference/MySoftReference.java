package com.basic.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
<<<<<<< HEAD
import java.sql.DriverManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
=======
>>>>>>> 63f8cca391a9c17372885b8a594e1c1149ecf056

/**
 * @author joker
 * @When
 * @Description
 * @Detail
 * @date 创建时间：2019-01-22 20:51
 */
public class MySoftReference
{


    public static class MySoftReferenceTestClass
    {
        public String name;
    }

    public void testWithOutQueue()
    {
        MySoftReferenceTestClass mySoftReferenceTestClass = new MySoftReferenceTestClass();
        mySoftReferenceTestClass.name = "joker";


        WeakReference<MySoftReferenceTestClass> softReference = new WeakReference<>(mySoftReferenceTestClass);
        MySoftReferenceTestClass mySoftReferenceTestClass1 = softReference.get();
        System.out.println(mySoftReferenceTestClass1.name);
        mySoftReferenceTestClass = null;
        mySoftReferenceTestClass1 = null;
        System.gc();


        MySoftReferenceTestClass testClass = softReference.get();
        testClass.name = "clown";
        if (null == testClass)
        {
            System.out.println("null");
        } else
        {
            System.out.println(testClass.name);
        }
    }


    public void testWithQueue()
    {
        MySoftReferenceTestClass mySoftReferenceTestClass = new MySoftReferenceTestClass();

        ReferenceQueue<MySoftReferenceTestClass> referenceQueue = new ReferenceQueue<>();
        SoftReference<MySoftReferenceTestClass> softReference = new SoftReference<>(mySoftReferenceTestClass, referenceQueue);




    }

    public static void main(String[] args)
    {


    }

}

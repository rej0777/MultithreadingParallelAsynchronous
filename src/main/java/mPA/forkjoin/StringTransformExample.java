package mPA.forkjoin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.sql.init.DatabaseInitializationSettings;

import mPA.utils.CommonUtil;
import mPA.utils.DataSet;
import mPA.utils.LoggerUtil;


public class StringTransformExample {


    public static void main(String[] args) {

    	CommonUtil.stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        names.forEach((name)->{
            String newValue = addNameLengthTransform(name);
            resultList.add(newValue);
        });
        CommonUtil.stopWatch.stop();
        LoggerUtil.log("Final Result : "+ resultList);
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
    	CommonUtil.delay(500);
        return name.length()+" - "+name ;
    }
}

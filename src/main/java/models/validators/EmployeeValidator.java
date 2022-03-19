package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.EmployeeView;
import constants.MessageConst;
import services.EmployeeService;

/**
 * 従業員インスタンスに設定されている値のバリデーションを行うクラス
 *
 */

public class EmployeeValidator {

    public static List<String> validate(
            EmployeeService service, EmployeeView ev, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag){
        List<String>errors = new ArrayList<String>();

        String codeError = validateCode(service, ev.getCode(), codeDuplicateCheckFlag);
        if(!codeError.equals("")) {
            errors.add(codeError);
        }

        String nameError = validateName(ev.getName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        String passError = validatePassword(ev.getPassword(),passwordCheckFlag);
        if(!passError.equals("")) {
            errors.add(passError);
        }

        return errors;
    }

    private static String validateCode(EmployeeService service, String code, Boolean codeDuplicateCheckFlag) {

        if(code == null || code.equals("")) {
            return MessageConst.E_NOEMP_CODE.getMessage();
        }

        if(codeDuplicateCheckFlag) {
            long employeesCount = isDuplicateEmployee(service, code);

            if(employeesCount > 0) {
                return MessageConst.E_EMP_CODE_EXIST.getMessage();
            }
        }

        return "";
    }

    private static  long isDuplicateEmployee(EmployeeService service, String code) {
        long employeeCount = service.countByCode(code);
        return employeeCount;
    }

    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        return "";
    }

    private static String validatePassword(String password, boolean passwordCheckFlag) {
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return MessageConst.E_NOPASSWORD.getMessage();
        }

        return"";
    }

}
package kr.co.trams.api.controllers.anonymous;

import kr.co.trams.api.controllers.BaseController;
import kr.co.trams.api.validations.ValidationUser;
import kr.co.trams.exceptions.ApplicationException;
import kr.co.trams.models.Error;
import kr.co.trams.models.Response;
import kr.co.trams.models.domains.Device;
import kr.co.trams.models.domains.Position;
import kr.co.trams.models.domains.Team;
import kr.co.trams.models.domains.User;
import kr.co.trams.models.enumeration.EnumMessage;
import kr.co.trams.models.form.FormDevice;
import kr.co.trams.models.form.FormReset;
import kr.co.trams.models.form.FormToken;
import kr.co.trams.models.form.FormUser;
import kr.co.trams.services.ServiceDevice;
import kr.co.trams.services.ServiceToken;
import kr.co.trams.services.ServiceUser;
import kr.co.trams.utils.EncryptionUtils;
import kr.co.trams.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Created by voncount on 7/21/16.
 */
@SuppressWarnings(value = {"unchecked, rawtype"})
@RestController(value = "apiAnonymousControllerAuthentication")
@RequestMapping(value = "/api/v1/anons/auth")
public class ControllerAuthentication extends BaseController {

    private ServiceUser serviceUser;
    private ServiceToken serviceToken;
    private ServiceDevice serviceDevice;
    private ValidationUser validationUser;

    public ControllerAuthentication(ServiceUser serviceUser, ServiceToken serviceToken, ServiceDevice serviceDevice, ValidationUser validationUser) {
        this.serviceUser = serviceUser;
        this.serviceToken = serviceToken;
        this.serviceDevice = serviceDevice;
        this.validationUser = validationUser;
    }

    @RequestMapping(
            value = "/phone/check", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object phoneCheck(
            @RequestBody FormUser form
    ) throws ApplicationException {
        Error error = validationUser.validateCheckPhone(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        } else {
            User user = serviceUser.findOne(form);
            if (user != null) {
                // find active device with this uuid
                FormDevice formDevice = new FormDevice();
                formDevice.setUserFk(user.getId());
                formDevice.setUuid(form.getUuid());

                Device existedDevice = serviceDevice.findOne(formDevice);
                if (existedDevice != null) {
                    error.reject("phone", EnumMessage.EXCEPTION_EXISTED.getMessage());
                    return RESPONSE(new Response<>(EnumMessage.EXCEPTION_EXISTED, error), HttpStatus.OK);
                }
            }
        }

        return SUCCESS_OK();
    }

    @RequestMapping(
            value = "/phone/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object phoneRegister (
            @RequestBody FormUser form
    ) throws ApplicationException {

        // validate request
        Error error = validationUser.validateRegisterPhone(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        }

        String token = serviceUser.registerSms(form);

//        return SUCCESS_OK();
        // TODO REMOVE WHEN PRODUCTION
        return SUCCESS(token);
    }

    @RequestMapping(
            value = "/phone/active", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object avtivatePhone (
            @RequestBody FormUser form
    ) throws ApplicationException {

        // validate request
        Error error = validationUser.validateActivatePhone(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        }

        int userId = serviceUser.activateSms(form);

        FormUser formUser = new FormUser();
        formUser.setId(userId);

        User user = serviceUser.findOne(formUser);

        Team team           = user.getTeam();
        Position position   = user.getPosition();

        Map<String, Object> result = JsonUtils.convert(user, Map.class);
        result.put("team", team.getName());
        result.put("position", position.getName());
        result.put("key", EncryptionUtils.generateToken(user.getId(), user.getUsername()));

        return SUCCESS(result);
    }

    @RequestMapping(
            value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object login (
            @RequestBody FormUser form
    ) throws ApplicationException {

        // validate request
        Error error = validationUser.validateLogin(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        }

        Response response = serviceUser.login(form);
        return RESPONSE(response, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/reset/findId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object findId (
            @ModelAttribute FormReset form
    ) throws ApplicationException {

        Error error = validationUser.validateFindId(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        }

        String username = serviceUser.findId(form);
        return SUCCESS(Collections.singletonMap("username", username));
    }

    @RequestMapping(
            value = "/reset/password", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object resetPasswordRequest (
            @ModelAttribute FormReset form
    ) throws ApplicationException {

        Error error = validationUser.validateFindPassword(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        }

        String token = serviceUser.findPassword(form);
        return SUCCESS_OK();
    }

    @RequestMapping(
            value = "/reset/password", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object resetPasswordConfirm (
            @RequestBody FormToken form
    ) throws ApplicationException {

        // validate request
        Error error = validationUser.validateResetPasswordConfirm(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        }

        serviceToken.validateResetToken(form);
        return SUCCESS_OK();
    }

    @RequestMapping(
            value = "/reset/password", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object resetPassword (
            @RequestBody FormUser form
    ) throws ApplicationException {

        // validate request
        Error error = validationUser.validateResetPassword(form);
        if (error.hasError()) {
            return FAIL_BAD_REQUEST(error);
        }

        serviceUser.resetPassword(form);
        return SUCCESS_OK();
    }

}

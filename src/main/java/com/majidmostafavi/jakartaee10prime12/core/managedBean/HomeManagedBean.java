package com.majidmostafavi.jakartaee10prime12.core.managedBean;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Person;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Year;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.PersonService;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.UserService;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.YearService;
import com.majidmostafavi.jakartaee10prime12.core.utils.CookieUtil;
import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import com.majidmostafavi.jakartaee10prime12.core.utils.SessionManager;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import org.primefaces.model.StreamedContent;
import java.io.IOException;
import java.util.*;

/**
 * Created by majid on 7/16/16.
 */
@Named
public class HomeManagedBean {

    @Inject
    private YearService yearService;
    @Inject
    private UserService userService;
    @Inject
    private MessageProvider messageProvider;
    @Inject
    private PersonService personService;

    private Year year;
    private String userInformation = "";
    private Person person;
    private List<Year> yearList;
//    private Set<Role> roleSet;
    private String description;

    private String oldPassword;
    private String newPassword;
    private String password;
    private String reEnterPassword;
//    private Users currentUser;

    private String logoAddress;

    private String url;
    private byte[] selectImage;
    private StreamedContent downloadFileTemp;
    private Map<String, byte[]> dataMap;

    private String suggestionPassword;
    private String personnelEnName;

    private int sessionTimeOutValue;


    @PostConstruct
    public void init(){
        dataMap= new HashMap<>();
//        yearList = yearService.findAllSortByYear();
//        year = SessionManager.g etYear();
//        userInformation =SessionManager.getPerson().getFirstName() + " " + SessionManager.getPerson().getLastName();
        userInformation ="شیرین" + " " + "فرخی";
        createLog();
//        SessionManager.setPerson(personService.findPersonById(SessionManager.getPerson().getId()));
//        person = SessionManager.getPerson();
 //       fetchCookie();
        sessionTimeOutValue =20;
    }

    private void createLog(){
        // todo: create log classes
/*        CoPersonEntryLog personEntryLog = new CoPersonEntryLog();
        personEntryLog.setPersonId(SessionManager.getPerson().getId());
        personEntryLog.setDate(new Date());
        personEntryLog.setRoleOrganization(SessionManager.getLoggedRoleOrganization().getId());
        personEntryLog.setTime(PersianCalendarUtil.currentTime());
        personEntryLog.setIP(JsfServletUtils.getClientIp());
        personEntryLog.setComputerName(JsfServletUtils.getClientInfo());
        personEntryLog.setType(true);
        personEntryLogService.createLog(personEntryLog);*/
//        personEntryLogService.createLog(true);
    }

    public void changeFiscalyear(){
        SessionManager.setYear(year);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        createCookie(facesContext);
    }

    //fixme : it refer to security module
    public void changeCurrentUserPassword() {
       /* currentUser = userService.findByUsername(SessionManager.getUsername());

        if (!Utils.sha1(oldPassword).equals(currentUser.getPassword())) {
            Utils.createMessage(messageProvider.getValue("message.general.current.password.error"),null, FacesMessage.SEVERITY_ERROR,true);
        } else {
            if (utilsPassword.changePasswordValidation(currentUser.getPassword(), newPassword, reEnterPassword)) {
                currentUser.setPassword(newPassword);

                if (userService.changePassword(currentUser) != 0) {
                    Utils.createMessage(messageProvider.getValue("message.general.password-changed"), null, FacesMessage.SEVERITY_INFO, true);
                } else {
                    Utils.createMessage(messageProvider.getValue("message.general.password-changed.fail"), null, FacesMessage.SEVERITY_INFO, true);
                }
            }
        }*/
    }

    private void createCookie(FacesContext facesContext){
        try{
            CookieUtil.setCookie(facesContext,Year.class.getSimpleName(), year.getId().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void fetchCookie(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String selectYear = CookieUtil.fetchCookie((HttpServletRequest) facesContext.getExternalContext().getRequest(),Year.class.getSimpleName());
        if(selectYear.isEmpty()){
            createCookie(facesContext);
        }else {
            this.year =yearService.findYearById(((Long.parseLong(selectYear))));
            SessionManager.setYear(this.year);
        }
    }

    public void gotoHome(){
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        //Get absolute and relative URL of the request
        String absolute = req.getRequestURL().toString();
        String relative = req.getServletPath();

        String path = absolute.substring(0, absolute.length() - relative.length());

        try {
            //Try redirecting to start page
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/home.xhtml");
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("loginform", new FacesMessage(FacesMessage.SEVERITY_ERROR, "This should not even happen...", "Try again!"));
            e.printStackTrace();
        }
    }

    //fixme : it refer to security module
    public void offerPassword() {
//        suggestionPassword = utilsPassword.offerPassword();
    }

    /* Setter And Getter */
    public String getUserInformation() {
        return userInformation;
    }
    public void setUserInformation(String userInformation) {
        this.userInformation = userInformation;
    }

    public List<Year> getYearList() {
        return yearList;
    }
    public void setYearList(List<Year> yearList) {
        this.yearList = yearList;
    }

    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getReEnterPassword() {
        return reEnterPassword;
    }
    public void setReEnterPassword(String reEnterPassword) {
        this.reEnterPassword = reEnterPassword;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getSelectImage() {
        return selectImage;
    }

    public void setSelectImage(byte[] selectImage) {
        this.selectImage = selectImage;
    }

    public Map<String, byte[]> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, byte[]> dataMap) {
        this.dataMap = dataMap;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public StreamedContent getDownloadFileTemp() {
        return downloadFileTemp;
    }

    public void setDownloadFileTemp(StreamedContent downloadFileTemp) {
        this.downloadFileTemp = downloadFileTemp;
    }

    public String getSuggestionPassword() {
        return suggestionPassword;
    }

    public void setSuggestionPassword(String suggestionPassword) {
        this.suggestionPassword = suggestionPassword;
    }

    public String getPersonnelEnName() {
        return personnelEnName;
    }

    public void setPersonnelEnName(String personnelEnName) {
        this.personnelEnName = personnelEnName;
    }

    public int getSessionTimeOutValue() {
        return sessionTimeOutValue;
    }

    public void setSessionTimeOutValue(int sessionTimeOutValue) {
        this.sessionTimeOutValue = sessionTimeOutValue;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}

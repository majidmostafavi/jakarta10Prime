package com.majidmostafavi.jakartaee10prime12.core.utils;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import com.majidmostafavi.jakartaee10prime12.core.entity.BaseView;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.persistence.Id;
import jakarta.servlet.ServletContext;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;


/**
 * Created by majid on 6/25/16.
 */
public class Utils {


    public static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }


    public static String sha1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static boolean verifyChecksum(String file, String testChecksum) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        FileInputStream fis = new FileInputStream(file);

        byte[] data = new byte[1024];
        int read = 0;
        while ((read = fis.read(data)) != -1) {
            sha1.update(data, 0, read);
        }
        ;
        byte[] hashBytes = sha1.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hashBytes.length; i++) {
            sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        String fileHash = sb.toString();

        return fileHash.equals(testChecksum);
    }

    public static InputStream getLogo() {
        String relativeWebPath = "/resources/images/TumsLogo.png";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        try {
//            File file = new File(absoluteDiskPath);
//            InputStream is = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
//            return is;
            return Files.newInputStream(Paths.get(absoluteDiskPath));
        } catch (IOException e) {
            return null;
        }
    }

    public static InputStream getBarcodeLogo() {
        String relativeWebPath = "/resources/images/TumsBarcodeLogo.png";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        try {
            return Files.newInputStream(Paths.get(absoluteDiskPath));
        } catch (IOException e) {
            return null;
        }
    }

    public static InputStream getLogoMin() {
        String relativeWebPath = "/resources/images/logoMin.png";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        try {
            return Files.newInputStream(Paths.get(absoluteDiskPath));
        } catch (IOException e) {
            return null;
        }
    }


//    public static List<CoPermission> loadAllPermissions() {
//        SortedSet<CoPermission> permissionsSet = new TreeSet<CoPermission>();
//        CoRoleOrganization roleOrganization = SessionManager.getLoggedRoleOrganization();
//        RoleDao roleDao = EjbManagedBean.getBean(RoleDao.class);
//        CoRole[] roles = roleOrganization.getRoles().toArray(new CoRole[0]);
//        for (int j = 0; j < roles.length; j++) {
//            CoRole role = roles[j];
//            //   permissionsSet.addAll(roleDao.findPermissionsByRole(role));
//        }
//        return new ArrayList<CoPermission>(permissionsSet);
//    }



//    public static boolean matchPassword(String oldPassword) {
//        if ((oldPassword != null) && (!SessionManager.getUser().getPassword().equals(oldPassword))) {
//            return false;
//        }
//        return true;
//    }

    public static List toList(Set set) {
        if (set != null) {
            return new ArrayList(set);
        }
        return new ArrayList();
    }


   /* public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() == 0) {
                return true;
            }
        } else if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return true;
            }
        } else if (obj instanceof Long) {
            Long l = (Long) obj;
            if (l == 0l) {
                return true;
            }
        } else if (obj instanceof Number) {
            Number n = (Number) obj;
            if ((Long)n == 0) {
                return true;
            }
        }
        return false;
    }*/

    public static void createMessage(String message, Object[] params, FacesMessage.Severity severity, boolean keepMessages) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (keepMessages) {
            Flash flash = context.getExternalContext().getFlash();
            flash.setKeepMessages(keepMessages);
        }
        message = MessageFormat.format(message, params);
        FacesMessage facesMessage = new FacesMessage(severity, "", message);
        context.addMessage(null, facesMessage);
    }


    public static String getMimeType(String fileName) {

        String[] extensions = {".jpg", ".jpeg", ".bmp", ".png", ".doc", ".pdf", ".docx"};

        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".gif")) {
            return "image/gif";
        } else if (fileName.endsWith(".bpm")) {
            return "image/bmp";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
            return "application/msword";
        } else if (fileName.endsWith(".pdf")) {
            return "application/pdf";
        } else {
            return "application/octet-stream";
        }
    }

    public static String getExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.indexOf(".") + 1);
        }
        return "";
    }


    public static boolean equalsSet(Set a1, Set a2) {
        if ((a1 == null && a2 != null) || (a1 != null && a2 == null)) {
            return false;
        }
        if (a1 == null) {
            return true;
        }
        if (a1 == a2) {
            return true;
        }
        return arrayEquals(a1.toArray(), a2.toArray());
    }

    public static boolean arrayEquals(Object[] a1, Object[] a2) {
        if ((a1 == null && a2 != null) || (a1 != null && a2 == null)) {
            return false;
        }
        if (a1 == null) {
            return true;
        }
        if (a1 == a2) {
            return true;
        }

        int length = a1.length;
        if (a2.length != length)
            return false;

        for (int i = 0; i < length; i++) {
            Object o1 = a1[i];
            Object o2 = a2[i];
            if (o1 instanceof Object[] ^ o2 instanceof Object[]) {
                return false;
            }
            if (o1 instanceof Object[]) {
                Object[] o11 = (Object[]) o1;
                Object[] o22 = (Object[]) o2;
                if (!equals(o11, o22)) {
                    return false;
                }
            }
            if (!equals(o1, o2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsContent(Object o1, Object o2) {
        if ((o1 == null && o2 != null) || (o1 != null && o2 == null)) {
            return false;
        }
        if (o1 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static boolean equals(Object o1, Object o2) {
        if ((o1 == null && o2 != null) || (o1 != null && o2 == null)) {
            return false;
        }
        if (o1 == null) {
            return true;
        }
        if ((o1 instanceof AbstractEntity) ||
                (o1 instanceof BaseView)
        ) {
            Field field1 = getIdField(o1);
            Field field2 = getIdField(o2);
            if (field1 != null && field2 != null) {
                try {
                    Method m1 = getGetterMethod(o1, field1);
                    Method m2 = getGetterMethod(o2, field2);
                    return m1.invoke(o1, null).equals(m2.invoke(o2, null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Method method1 = getIdGetterMethod(o1);
            Method method2 = getIdGetterMethod(o2);
            if (method1 != null && method2 != null) {
                try {
                    Object obj1 = method1.invoke(o1, null);
                    Object obj2 = method1.invoke(o2, null);
                    if (obj1 == null && obj2 == null) {
                        return true;
                    } else if (obj1 != obj2) {
                        return false;
                    } else {
                        return obj1.equals(obj2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return o1.equals(o2);
        } else {
            return equalsContent(o1, o2);
        }
    }

    private static Method getGetterMethod(Object obj, Field field) throws NoSuchMethodException {
        String methodName = "get" + Character.toUpperCase(field.getName().charAt(0)) + (field.getName().length() > 1 ? field.getName().substring(1) : "");
        return obj.getClass().getMethod(methodName, null);
    }

    public static Method getGetterMethod(Object obj, String field) throws NoSuchMethodException {
        String methodName = "get" + Character.toUpperCase(field.charAt(0)) + (field.length() > 1 ? field.substring(1) : "");
        return obj.getClass().getMethod(methodName, null);
    }

    public static Field getIdField(Object obj) {
        Class clazz = obj.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    return field;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    public static Method getIdGetterMethod(Object obj) {
        Class clazz = obj.getClass();
        while (clazz != null) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Id.class)) {
                    return method;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    public static Method getEntityManagerMethod(Object obj) throws NoSuchMethodException {
        Class clazz = obj.getClass();
        if (clazz != null) {
            return clazz.getDeclaredMethod("getEntityManager", null);
        }
        return null;
    }


    public static <T> List<T> cast(List objects, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        for (Object obj : objects) {
            if (obj != null)
                result.add(clazz.cast(obj));
        }
        return result;
    }


    public static Object getIdValue(Object obj) {
        try {
            Method method;
            Field field = getIdField(obj);
            if (field != null) {
                method = getGetterMethod(obj, field);
            } else {
                method = getIdGetterMethod(obj);
            }
            if (method != null) {
                return method.invoke(obj, null);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> boolean listEqualsNoOrder(List<T> l1, List<T> l2) {
        final Set<T> s1 = new HashSet<>(l1);
        final Set<T> s2 = new HashSet<>(l2);

        return s1.equals(s2);
    }

    public static <T extends AbstractEntity> boolean compareList(List<T> l1, List<T> l2) {
        boolean b = true;
        for (Object independent : l1) {
            if (isContain(l2,independent)) {
                b = false;
            }
        }
        return b;
    }

    public static <T extends AbstractEntity> boolean isContain(List<T> s1, Object o){
        for (AbstractEntity b :s1){
            if (b.getClass().equals(o.getClass())&& getIdValue(b).equals(getIdValue(o))){
                return true;
            }
        }
        return false;
    }

    public static <T>  List<T> predicateAnd(Predicate<T> p1 , Predicate<T> p2 ,List<T> list){
        List<T> returnList = new ArrayList<T>();
        list.stream().forEach(t->{
            if (p1.and(p2).test(t)) returnList.add(t);
        });
        return returnList;
    }

    public static <T>  List<T> predicateAndAnd(Predicate<T> p1 , Predicate<T> p2 , Predicate<T> p3, List<T> list){
        List<T> returnList = new ArrayList<T>();
        list.stream().forEach(t->{
            if (p1.and(p2).and(p3).test(t)) returnList.add(t);
        });
        return returnList;
    }

    public static <T>  List<T> predicateOr(Predicate<T> p1 , Predicate<T> p2 ,List<T> list) {
        List<T> returnList = new ArrayList<T>();
        list.stream().forEach(t -> {
            if (p1.or(p2).test(t))
                returnList.add(t);
        });
        return returnList;
    }
    public static <T>  List<T> predicate(Predicate<T> p1 ,List<T> list) {
        List<T> returnList = new ArrayList<T>();
        list.stream().forEach(t -> {
            if (p1.test(t))
                returnList.add(t);
        });
        return returnList;
    }

/*    public static CmFiscalyear findFiscalyear(List<CmFiscalyear> fiscalyears, Date date){
        return fiscalyears
                .parallelStream()
                .filter(fiscalyear-> PersianCalendarUtil.dateBetween(fiscalyear.getStartDate(),fiscalyear.getEndDate(),date))
                .findFirst()
                .get();
    }*/

    /*public static  <T extends CmIndependent>  List<T> treeWithParent(List<T> list){
        SortedSet<T> hashSet = new TreeSet<>(list);
        list.forEach(independent -> traversIndependent(independent, hashSet));
        return  new ArrayList<>(hashSet);
    }

    private static <T extends CmIndependent> void  traversIndependent(T source,SortedSet<T> set){
        if (source!=null){
            set.add(source);
            if (source.getParentId()!=null){
                traversIndependent((T) source.getParent(),set);
            }
        }
    }


    public static List<CmIndependent>  searchIndependent(List<CmIndependent> independents,String independentNameSearch, String independentCodeSearch) {
        List<CmIndependent> independentList = new ArrayList<>();

        Predicate<CmIndependent> namePredicate = (p) -> p.getName().contains(independentNameSearch);
        Predicate<CmIndependent> codePredicate = (p) -> p.getCode().contains(independentCodeSearch);
        independents.forEach(independent -> {
            if (independent.isLeaf()) {
                if (independentNameSearch != null && !independentNameSearch.isEmpty() && independentCodeSearch != null && !independentCodeSearch.isEmpty()) {
                    if (namePredicate.test(independent) && codePredicate.test(independent))
                        independentList.add(independent);
                } else if (independentNameSearch != null && !independentNameSearch.isEmpty()&& namePredicate.test(independent)) {
                    independentList.add(independent);
                } else if (independentCodeSearch != null && !independentCodeSearch.isEmpty()&& codePredicate.test(independent)) {
                    independentList.add(independent);
                }
            }
        });
        return independentList;
    }

    public static List<CmIndependent>  searchIndependent(List<CmIndependent> independents, String independentNameSearch, String independentCodeSearch, CmIndependentAccount independentAccount) {
        List<CmIndependent> independentList = new ArrayList<>();

        Predicate<CmIndependent> namePredicate = (p) -> p.getName().contains(independentNameSearch);
        Predicate<CmIndependent> codePredicate = (p) -> p.getCode().contains(independentCodeSearch);
        independents.forEach(independent -> {
            if (!independentAccount.isOnlyLeaf() || (independentAccount.isOnlyLeaf() && independent.isLeaf())) {
                if (independentNameSearch != null && !independentNameSearch.isEmpty() && independentCodeSearch != null && !independentCodeSearch.isEmpty()) {
                    if (namePredicate.test(independent) && codePredicate.test(independent))
                        independentList.add(independent);
                } else if (independentNameSearch != null && !independentNameSearch.isEmpty()&& namePredicate.test(independent)) {
                    independentList.add(independent);
                } else if (independentCodeSearch != null && !independentCodeSearch.isEmpty()&& codePredicate.test(independent)) {
                    independentList.add(independent);
                }
            }
        });
        return independentList;
    }

    public static List<CmIndependent>  searchIndependent(List<CmIndependent> independents, String independentSearch, CmIndependentAccount independentAccount) {
        List<CmIndependent> independentList = new ArrayList<>();

        Predicate<CmIndependent> namePredicate = (p) -> p.getName().contains(independentSearch);
        Predicate<CmIndependent> codePredicate = (p) -> p.getCode().contains(independentSearch);
        independents.forEach(independent -> {
            if (!independentAccount.isOnlyLeaf() || (independentAccount.isOnlyLeaf() && independent.isLeaf())) {
                if (!independentSearch.isEmpty()) {
                    if (namePredicate.test(independent) || codePredicate.test(independent))
                        independentList.add(independent);
                }
            }
        });
        return independentList;
    }*/

    public static byte[] getImageOriginalData(InputStream inputStream) throws Exception {
        try {
            ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];

            int bulk;
            while ((bulk = inputStream.read(buffer)) >= 0) {
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();
            return fileOutputStream.toByteArray();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static Byte[] bytePrimitiveToReference(byte[] bytes) {
        Byte[] arr = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            arr[i] = bytes[i];
        }
        return arr;
    }

    public static byte[] byteReferenceToPrimitive(Byte[] bytes) {
        byte[] arr = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            arr[i] = bytes[i];
        }
        return arr;
    }
}

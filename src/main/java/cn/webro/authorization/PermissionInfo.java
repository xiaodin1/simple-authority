package cn.webro.authorization;

import cn.webro.authorization.annotation.Permission;
import com.sun.istack.internal.NotNull;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PermissionInfo
 * 方法的权限信息(对Permission注解的封装)
 *
 * @author TuWei
 * @date 15/8/19
 */
public class PermissionInfo {

    public PermissionInfo(){
        super();
        this.urls = new HashSet<String>();
        this.subPermission = new ArrayList<PermissionInfo>();
    }

    /**
     * 根据Class(节点,没有实际意义), 实例化一个PermissionInfo
     * @param clazz 节点Permission
     */
    public PermissionInfo(@NotNull Class<?> clazz) {
        if(clazz != null){
            Permission pPerms = clazz.getAnnotation(Permission.class);
            if(pPerms != null){
                name = pPerms.value();
                description = pPerms.desc();
            }else{
                name = clazz.getSimpleName();
            }
            isNode = true;
            uniqueId = clazz.getName();
            subPermission = new ArrayList<PermissionInfo>();
        }
    }

    /**
     * 权限标识
     */
    protected int identity;

    /**
     * 父级权限标识
     */
    protected int parentIdentity;

    /**
     * 权限分组
     */
    protected String group;

    /**
     * 权限名
     */
    protected String name;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 该权限信息是一个节点, 主要用来权限归类, 无实际作用
     */
    private boolean isNode;

    /**
     * 访问方法名
     */
    private String methodName;

    /**
     * 访问方法
     */
    private Method method;

    /**
     * 访问url
     */
    private Set<String> urls;

    /**
     * 该权限(节点)下的Permission
     */
    private List<PermissionInfo> subPermission;

    /**
     * 无权限跳转的页面(覆盖Config中的配置)
     */
    private String failureUrl;

    /**
     * 方法返回视图的类型
     */
    private int viewType;

    /**
     * 定义权限目标方法的类型(菜单? 按钮)
     */
    private int type;

    /**
     * 方法的唯一标示
     */
    private String uniqueId;

    /**
     * 是否显示
     */
    private boolean display;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public String getMethodName() {
        return methodName;
    }

    public Method getMethod() {
        return method;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public int getViewType() {
        return viewType;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public boolean isNode() {
        return isNode;
    }

    public List<PermissionInfo> getSubPermission() {
        return subPermission;
    }

    /**
     * 该权限是否包含某个url
     *
     * @param url 需要匹配的url("/"开始,不带后缀)
     */
    public boolean containsUrl(String url) {
        if (!Strings.isEmpty(url) && !Lang.isEmpty(urls)) {
            return urls.contains(url);
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsNode(boolean isNode) {
        this.isNode = isNode;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public void setSubPermission(List<PermissionInfo> subPermission) {
        this.subPermission = subPermission;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getParentIdentity() {
        return parentIdentity;
    }

    public void setParentIdentity(int parentId) {
        this.parentIdentity = parentId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "PermissionInfo{" +
                "name='" + name + '\'' +
                ", methodName='" + methodName + '\'' +
                ", subPermission=" + subPermission +
                '}';
    }
}

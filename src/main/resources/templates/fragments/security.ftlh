<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>
<#if know>
    <#assign
        loggedUser =Session.SPRING_SECURITY_CONTEXT.authentication.principal
        login= loggedUser.getUsername()
        isAdmin = loggedUser.toString()?contains("ADMIN")
    >

<#else>
    <#assign
        name = "guest"
        guest = true
        isAdmin = false
    >
</#if>
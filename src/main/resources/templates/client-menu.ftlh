
<head>
    <#include "fragments/bootstrap.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Client menu</title>
</head>

<body>
<#include "fragments/nav-bar.ftl" />
<br>

<div class="container">
    <div class="row">
        <#if isAdmin>
            <h2 class="text"><@spring.message "navbar.client"/></h2>
        <#else>
            <h2 class="text"><@spring.message "title.main.menu"/></h2>
        </#if>

        <table class="table table-bordered success">
            <thead>
            <tr>
                <th><@spring.message "field.user.login"/></th>
                <td>${user.id?long?c}</td>
            </tr>
            <#if springMacroRequestContext.getLocale().toString()?contains("en")>
                <tr>
                    <th><@spring.message "field.user.name"/></th>
                    <td>${user.getNameEn()}</td>
                </tr>
            <#else>
                <tr>
                    <th><@spring.message "field.user.name"/></th>
                    <td>${user.getNameUkr()}</td>
                </tr>
            </#if>
            <tr>
                <th class="info"><@spring.message "field.user.email"/></th>
                <td>${user.getEmail()}</td>
            </tr>
            <tr>
                <th class="info"><@spring.message "field.user.phone"/></th>
                <td>${user.getPhone()}</td>
            </tr>
            <tr>
                <th class="info"><@spring.message "field.user.address"/></th>
                <td>${user.getAddress()}</td>
            </tr>

            <tr>
                <th class="info"><@spring.message "field.user.balance"/></th>
                <#if user.getBalance()<0>
                    <td style="color: crimson">${user.getBalance()?string(",##0.00")} <@spring.message "currency"/></td>
                <#else>
                    <td>${user.getBalance()?string(",##0.00")} <@spring.message "currency"/></td>
                </#if>
            </tr>
            <tr>
                <th><@spring.message "field.user.status"/></th>
                <#if user.getUserStatus() == "ACTIVE">
                    <td><@spring.message "field.user.active"/></td>
                <#else>
                    <td style="color: crimson"><@spring.message "field.user.blocked"/></td>
                </#if>
            </tr>
            <tr>
                <#if !isAdmin>
                    <form class="form-inline ml-auto my-2 my-lg-0"
                          action="/client/payment/" method="post">
                        <th>
                            <input type="number" step="0.01" id="payment" class="form-control" name="payment" placeholder="<@spring.message "field.user.payment"/>" required >
                        </th>
                        <td>
                            <button class="btn btn-primary " type="submit"><@spring.message "field.user.pay"/></button>
                        </td>
                    </form>
                </#if>
            </tr>

            <tr>
                <th scope="col" colspan="2"><@spring.message "field.user.tariffs"/></th>

                <#list tariffs as tariff>
                <tr>
                    <th>
                        <#if tariff.service=='internet'><@spring.message "navbar.internet"/></#if>
                        <#if tariff.service=='tv'><@spring.message "navbar.tv"/></#if>
                        <#if tariff.service=='telephony'><@spring.message "navbar.telephony"/></#if>
                    </th>

                    <td>
                        <#if springMacroRequestContext.getLocale().toString()?contains("en")>
                            ${tariff.nameEn}
                        <#else>
                            ${tariff.nameUkr}
                        </#if>
                    </td>
                </tr>
            </#list>
            </tr>
            </thead>
        </table>
        <#if isAdmin>
            <div class="col-md-6 offset-md-4">
                <button type="button" class="btn btn-primary hBack" onclick="history.back()" style="background-color: dodgerblue">
                    <@spring.message "button.back"/>
                </button>
            </div>
        </#if>
    </div>
</div>
<br>
<br>
<#include "fragments/footer.ftlh"/>
</body>
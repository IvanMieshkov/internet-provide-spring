
<head>
    <#include "../fragments/bootstrap.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Client menu</title>
</head>

<body>
<#include "../fragments/nav-bar.ftl" />
<br>

<div class="container">
    <div class="row">
        <h2 class="text"><@spring.message "title.main.menu"/></h2>
        <#--        <form class="form-inline ml-auto my-2 my-lg-0"-->
        <#--              action="/api/candidate/edit/${user.getId()}" method="get">-->
        <#--            <button class="btn btn-primary " type="submit"><@spring.message "profile.edit.edit_profile"/></button>-->
        <#--        </form>-->

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
                <#if user.getUserStatus() == "ACTIVE">
                    <td>${user.getBalance()} <@spring.message "currency"/></td>
                    <td><@spring.message "field.user.active"/></td>
                <#else>
                    <td style="color: crimson">${user.getBalance()} <@spring.message "currency"/></td>
                    <td><@spring.message "field.user.blocked"/></td>
                </#if>

            </tr>
            <tr>
                <form class="form-inline ml-auto my-2 my-lg-0"
                      action="/client/payment/" method="post">
                    <th>
                        <input type="number" id="payment" class="form-control" name="payment" placeholder="<@spring.message "field.user.payment"/>" required >
                    </th>
                    <td>
                        <button class="btn btn-primary " type="submit"><@spring.message "field.user.pay"/></button>
                    </td>
                </form>
            </tr>


            </thead>

        </table>
        <div class="row">
            <h3 class="text"><@spring.message "field.user.tariffs"/></h3>
            <table class="table table-bordered success">
                <tbody>
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
                </tbody>

            </table>
        </div>


    </div>
</div>
<br>
<br>
<#include "../fragments/footer.ftlh"/>
</body>
<#macro error>
    <#import "/spring.ftl" as spring/>
    <#if errorMessage??>

        <div class="alert alert-primary" role="alert">
            ${errorMessage}
        </div>

    <#else>
    </#if>

</#macro>
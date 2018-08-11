package org.jenkinsci.plugins.ghprb;

// import com.cloudbees.jenkins.GitHubWebHook;
// import com.google.common.base.Predicate;
// import com.google.common.base.Predicates;
import hudson.Extension;
// import hudson.XmlFile;
// import hudson.model.Descriptor;
// import hudson.model.Item;
// import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;
// import jenkins.model.Jenkins;
import net.sf.json.JSONObject;
// import org.apache.commons.codec.binary.Base64;
// import org.jenkinsci.main.modules.instance_identity.InstanceIdentity;
// import org.jenkinsci.plugins.github.GitHubPlugin;
// import org.jenkinsci.plugins.github.Messages;
// import org.jenkinsci.plugins.github.internal.GHPluginConfigException;
// import org.jenkinsci.plugins.github.migration.Migrator;
// import org.kohsuke.accmod.Restricted;
// import org.kohsuke.accmod.restrictions.DoNotUse;
// import org.kohsuke.github.GitHub;
// import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
// import org.kohsuke.stapler.interceptor.RequirePOST;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import javax.inject.Inject;
// import java.io.IOException;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.security.interfaces.RSAPublicKey;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// import static com.google.common.base.Charsets.UTF_8;
// import static java.lang.String.format;
// import static org.apache.commons.lang3.StringUtils.isNotEmpty;
// import static org.jenkinsci.plugins.github.config.GitHubServerConfig.allowedToManageHooks;
// import static org.jenkinsci.plugins.github.config.GitHubServerConfig.loginToGithub;
// import static org.jenkinsci.plugins.github.internal.GitHubClientCacheOps.clearRedundantCaches;
// import static org.jenkinsci.plugins.github.util.FluentIterableWrapper.from;

/**
 * Global configuration to store all GH Plugin settings
 * such as hook managing policy, credentials etc.
 *
 * @author lanwen (Merkushev Kirill)
 * @since 1.13.0
 */
@Extension
public class GhprbTriggerGlobalConfig extends GlobalConfiguration {
    // private static final Logger LOGGER = LoggerFactory.getLogger(GhprbTriggerGlobalConfig.class);

    // @todo: add this
    // public static final String GITHUB_PLUGIN_CONFIGURATION_ID = "github-plugin-configuration";

    /**
     * Helps to avoid null in {@link GitHubPlugin#configuration()}
     */
    // public static final GhprbTriggerGlobalConfig EMPTY_CONFIG =
    //         new GhprbTriggerGlobalConfig(Collections.<GitHubServerConfig>emptyList());

    // private List<GitHubServerConfig> configs = new ArrayList<GitHubServerConfig>();
    // private URL hookUrl;
    // private HookSecretConfig hookSecretConfig = new HookSecretConfig(null);

    private boolean useDetailedComments;

    // private transient boolean overrideHookUrl;

    // /**
    //  * Used to get current instance identity.
    //  * It compared with same value when testing hook url availability in {@link #doCheckHookUrl(String)}
    //  */
    // @Inject
    // @SuppressWarnings("unused")
    // private transient InstanceIdentity identity;

    public GhprbTriggerGlobalConfig() {
        load();
    }

    // public GhprbTriggerGlobalConfig(List<GitHubServerConfig> configs) {
    //     this.configs = configs;
    // }

    // @SuppressWarnings("unused")
    // public void setConfigs(List<GitHubServerConfig> configs) {
    //     this.configs = configs;
    // }

    // public List<GitHubServerConfig> getConfigs() {
    //     return configs;
    // }

    public void setUseDetailedComments(boolean useDetailedComments) {
        if (useDetailedComments) {
            this.useDetailedComments = useDetailedComments;
        } else {
            this.useDetailedComments = false;
        }
    }

    public boolean getUseDetailedComments() {
        return useDetailedComments;
    }

    // /**
    //  * To avoid long class name as id in xml tag name and config file
    //  */
    // @Override
    // public String getId() {
    //     return GITHUB_PLUGIN_CONFIGURATION_ID;
    // }

    // /**
    //  * @return config file with global {@link com.thoughtworks.xstream.XStream} instance
    //  * with enabled aliases in {@link Migrator#enableAliases()}
    //  */
    // @Override
    // protected XmlFile getConfigFile() {
    //     return new XmlFile(Jenkins.XSTREAM2, super.getConfigFile().getFile());
    // }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        try {
            req.bindJSON(this, json);
        } catch (Exception e) {
            // LOGGER.debug("Problem while submitting form for GitHub Plugin ({})", e.getMessage(), e);
            // LOGGER.trace("GH form data: {}", json.toString());
            // throw new FormException(
            //         format("Malformed GitHub Plugin configuration (%s)", e.getMessage()), e, "github-configuration");
        }
        save();
        // clearRedundantCaches(configs);
        return true;
    }

    @Override
    public String getDisplayName() {
        return "GitHub Trigger Global Config";
    }

    // @SuppressWarnings("unused")
    // @RequirePOST
    // public FormValidation doReRegister() {
    //     Jenkins.getActiveInstance().checkPermission(Jenkins.ADMINISTER);
    //     if (!GitHubPlugin.configuration().isManageHooks()) {
    //         return FormValidation.warning("Works only when Jenkins manages hooks (one or more creds specified)");
    //     }

    //     List<Item> registered = GitHubWebHook.get().reRegisterAllHooks();

    //     LOGGER.info("Called registerHooks() for {} items", registered.size());
    //     return FormValidation.ok("Called re-register hooks for %s items", registered.size());
    // }
}

j = namespace("jelly:core")
f = namespace("/lib/form")

f.section(title: descriptor.displayName) {
  f.entry(field: "useDetailedComments", title: _("Use comments to report intermediate phases: triggered et al")) {
    f.checkbox()
  }
}

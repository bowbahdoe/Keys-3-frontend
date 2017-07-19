(ns ^:figwheel-no-load keys-frontend.dev
  (:require
    [keys-frontend.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)

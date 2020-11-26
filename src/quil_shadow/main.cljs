(ns quil-shadow.main
  (:require [devtools.core :as devtools]))

(devtools/install!)

(defn main! []
  (js/console.log {:hello [1 2 3]}))
(ns quil-shadow.main
  (:require [devtools.core :as devtools]
            [quil.core :as q :include-macros true]))

(devtools/install!)


(defn setup []
  (q/frame-rate 10)
  (q/set-state! :image (q/load-image "pocky.png")))

(defn draw []
  (q/no-stroke)
  (q/background 230)
  (let [im (q/state :image)
        modes [[:threshold] [:threshold 0.2] [:gray] [:posterize 20]
               [:blur] [:opaque]]]
    (when (q/loaded? im)
      (q/image im 0 0)
      (dotimes [i (count modes)]
        (let [mode (get modes i)
              dest (q/create-graphics (.-width im) (.-height im))]
          (q/with-graphics dest
            (q/image im 0 0)
            (apply q/display-filter mode))
          (q/image dest (* (inc i) (.-width im)) 0))))))

(q/defsketch filters
  :host "host"
  :size [900 600]
  :setup setup
  :draw draw)

(defn main! []
  (js/console.log "starting main…"))

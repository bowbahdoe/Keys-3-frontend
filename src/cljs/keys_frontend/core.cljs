(ns keys-frontend.core
    (:require [keys-frontend.render :refer [render Location Key]]))

(defn init! [] nil)
(render {:board {:unlocked {(Location. 1 2) (Key. :north :gold)}
                 :locked {}}
         :moves (list (Location. 1 1))
         :rotations '()} "keys_canvas")

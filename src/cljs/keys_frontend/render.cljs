(ns keys-frontend.render)

;; A GameState is a
;;	{:board Board
;;	 :turn Team
;;	 :moves |List Location|
;;	 :rotations |List Location|}

;; ----------------------------------------------------------------------------
;; A Board is a {:unlocked |Mapping Location Key|,
;;              :locked |Mapping Location Team|}

;; ----------------------------------------------------------------------------
;; A Location is a (defrecord Location [Int Int])
(defrecord Location [x y])

;; ----------------------------------------------------------------------------
;; A Key is a (defrecord Key [Orientation Team])
(defrecord Key [orientation team])

;; ----------------------------------------------------------------------------
;; A Orientation is one of
;; -- :north
;; -- :south
;; -- :east
;; -- :west
;; -- :norteast
;; -- :northwest
;; -- :southeast
;; -- :southwest

;; ----------------------------------------------------------------------------
;; A Team is one of
;; -- :gold
;; -- :silver

;; ----------------------------------------------------------------------------
;; A Context is
;; -- a javascript object that provides methods and
;;    properties for drawing on the canvas.

;; ----------------------------------------------------------------------------
;; A Color is one of
;; -- the 140 names listed here https://www.w3schools.com/tags/ref_colornames.asp
;; -- An Integer in the range 0x000000 > 0xFFFFFF

;; ----------------------------------------------------------------------------

;; Board, Int, Int, Context & {:color-1 Color :color-2 Color}
;; Renders the board onto the given canvas Context
(defn- render-background [board width height ctx & {:keys [color-1 color-2]
                                                    :or {color-1 "black"
                                                        color-2 "white"}}]
  (let [cell-size (/ (min width height) 8)
        old-fill (.-fillStyle ctx)]
    (dotimes [row 8]
      (dotimes [column 8]
        (if (not= (mod column 2) (mod row 2))
            (do (set! (.-fillStyle ctx) color-1)
                (.fillRect ctx (* cell-size column) (* cell-size row) cell-size cell-size))
            (do (set! (.-fillStyle ctx) color-2)
                (.fillRect ctx  (* cell-size column) (* cell-size row) cell-size cell-size)))))
      (set! (.-fillStyle ctx) old-fill)))

;; |List Location|, Int, Int, Context -> nil
;; renders the moves that are available to the player
(defn- render-moves [moves width height ctx]
  (doseq [move moves]
    (prn move))
  nil)

;; |List Location|, Int, Int, Context -> nil
;; renders the rotations that are available to the player
(defn- render-rotations [rotations width height ctx]
  (doseq [rotation rotations]
    (prn rotation))
  nil)

;; |Mapping Location Team|, Int, Int, Context -> nil
;; renders the pieces that are locked
(defn- render-locked [locked width height ctx] nil)

;; |Mapping Location Key|, Int, Int, Context -> nil
;; renders the pieces that are unlocked
(defn- render-unlocked [unlocked width height ctx]
  (doseq [keyval unlocked] (prn keyval)))

;; Board, Int, Int, Context -> nil
;; Renders the game board
(defn- render-game-state [game-state width height ctx]
  (let [board (:board game-state)]
    (render-background board width height ctx)
    (render-rotations (:rotations game-state) width height ctx)
    (render-moves (:moves game-state) width height ctx)
    (render-locked (:locked board) width height ctx)
    (render-unlocked (:unlocked board) width height ctx)))

;; GameState, String -> nil
;; Renders the gamestate to the canvas with the given id
(defn render [game-state canvas-id]
  (let [canvas (.getElementById js/document canvas-id)
        ctx (.getContext canvas "2d")
        width (.-width canvas)
        height (.-height canvas)]
        (render-game-state game-state width height ctx)))

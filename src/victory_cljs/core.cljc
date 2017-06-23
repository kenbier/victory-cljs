(ns victory-cljs.core
 #?@(:clj [(:require
            [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as csk.extras]
            [sablono.core :as s])]
     :cljs [(:require
             [cljsjs.victory]
             [sablono.core])
            (:require-macros
             [victory-cljs.core])]))

#?(:clj (def ^:private class-symbols
          '[Area
            Bar
            BrushHelpers
            Candle
            ClipPath
            Collection
            Curve
            Data
            DefaultTransitions
            Domain
            ErrorBar
            Events
            Flyout
            Helpers
            Line
            Log
            Point
            Portal
            PropTypes
            Scale
            SelectionHelpers
            Slice
            Style
            TextSize
            Transitions
            VictoryAnimation
            VictoryArea
            VictoryAxis
            VictoryBar
            VictoryBrushContainer
            VictoryCandlestick
            VictoryChart
            VictoryClipContainer
            VictoryContainer
            VictoryErrorBar
            VictoryGroup
            VictoryLabel
            VictoryLegend
            VictoryLine
            VictoryPie
            VictoryPortal
            VictoryScatter
            VictorySelectionContainer
            VictorySharedEvents
            VictoryStack
            VictoryTheme
            VictoryTooltip
            VictoryTransition
            VictoryVoronoi
            VictoryVoronoiTooltip
            VictoryZoom
            VictoryZoomContainer
            Voronoi
            ZoomHelpers
            addEvents]))

#?(:clj (defn compile-child [child]
     `(sablono.core/html ~child)))

#?(:clj (defn ^:private class-symbol->constructor [class-symbol]
          `(defmacro ~(csk/->kebab-case-symbol class-symbol) [& args#]
             (let [[attrs# & [children#]] (if (or (-> args# first map?)
                                                  (-> args# first nil?))
                                            [(first args#) (rest args#)]
                                            [nil args#])

                   attrs# (csk.extras/transform-keys csk/->camelCaseString attrs#)]
               `(js/React.createElement
                 ~(symbol "js" (str "Victory." (name '~class-symbol)))
                 (cljs.core/clj->js ~attrs#)
                 ~@(map compile-child children#))))))

#?(:clj (defmacro ^:private def-constructors []
          `(do
             ~@(clojure.core/map class-symbol->constructor class-symbols))))

#?(:clj (def-constructors))

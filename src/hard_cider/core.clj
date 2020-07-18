(ns hard-cider.core)

;; basic evaluation examples
(+ 1 2 3)

(range 1 20)

(reduce + (range 1 100))

(.toUpperCase "bozhidar")

;; Let's define some functions!
(defn hello-world []
  "Hello, world!")

(hello-world)

(defn greeting [name]
  (if (= name "Bozhidar")
    (str "Hello, Master of the Universe and Emacs!")
    (str "Hello, " name)))

(greeting "Joan")
(greeting "Bozhidar")

;; tail recursion
(defn fact-rec [n]
  (if (= n 1)
    1
    (*' n (fact-rec (dec n)))))

(defn fact-iter [n]
  (reduce *' (range 1 (inc n))))

(fact-rec 5)
(fact-iter 5)

;; the output from those expressions in end
;; in your REPL buffer
(time (fact-rec 100))
(time (fact-iter 100))

;; tree recursion
(defn fibo-rec [n]
  (case n
    0 0
    1 1
    (+' (fibo-rec (dec n)) (fibo-rec (- n 2)))))

(defn fibo-iter
  ([n] (fibo-iter 0 1 n))
  ([curr nxt n]
   (cond
     (zero? n) curr
     :else (recur nxt (+' curr nxt) (dec n)))))

(fibo-rec 10)
(fibo-iter 10)

(time (fibo-rec 30))
(time (fibo-iter 30))

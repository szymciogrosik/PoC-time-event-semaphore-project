package smo;

import java.util.LinkedList;

    public class Kolejka {
        private LinkedList<Zgloszenie> kolejka;
        // 0 - rozmiar nieustalony, każda inna dodatnia liczba wyznacza rozmiar
        private int max = 1;
        // Fifo - fifoOrLifo == 0 | Lifo - fifoOrLifo == 1
        private int fifoOrLifo = 1;
        // Priorytetowa 1, Niepriorytetowa 0
        private int priorytetowaLubNiepriorytetowa = 0;

        public Kolejka() {
            // Utworzenie wewnętrznej listy w kolejce
            this.kolejka = new LinkedList<Zgloszenie>();
        }

        public Kolejka(int dlugoscKolejki, int fifoOrLifo , int priorytetowaLubNiepriorytetowa) {
            // Utworzenie wewnętrznej listy w kolejce
            this.kolejka = new LinkedList<Zgloszenie>();
            this.max = dlugoscKolejki;
            this.fifoOrLifo = fifoOrLifo;
            this.priorytetowaLubNiepriorytetowa = priorytetowaLubNiepriorytetowa;
        }

        // Wstawienie zgłoszenia do kolejki
        public void dodaj(Zgloszenie zgloszenie) {

            //Jeżeli rozmiar ustalony
            if(max != 0) {
                //Czy priorytetowa z ustalonym rozmiarem
                if(priorytetowaLubNiepriorytetowa == 1) {
                    if (kolejka.size() < max) {
                        int i = 0;
                        //Lifo - jeśli fifoOrLifo == 1
                        //Fifo - jeśli fifoOrLifo == 0
                        i = fifoOrLifo == 1 ? szukajMiejscaLifo(zgloszenie) : szukajMiejscaFifo(zgloszenie);
                        kolejka.add(i, zgloszenie);
                    } else {
                        System.out.println("Blad elementow jest za dużo - kolejka SMO!");
                    }
                } else {
                    //Niepriorytetowa z ustalonym rozmiarem
                    //Lifo
                    if(fifoOrLifo == 1) {
                        if (kolejka.size() < max) {
                            kolejka.addLast(zgloszenie);
                        } else {
                            System.out.println("Blad elementow jest za dużo - kolejka SMO!");
                        }
                    } else {
                        //Fifo
                        if (kolejka.size() < max) {
                            kolejka.addFirst(zgloszenie);
                        } else {
                            System.out.println("Blad elementow jest za dużo - kolejka SMO!");
                        }
                    }
                }
            } else {
                //Kolejka nieskończona
                //Kolejka z priorytetami
                if(priorytetowaLubNiepriorytetowa == 1) {
                    int i = 0;
                    //Lifo - jeśli fifoOrLifo == 1
                    //Fifo - jeśli fifoOrLifo == 0
                    i = fifoOrLifo == 1 ? szukajMiejscaLifo(zgloszenie) : szukajMiejscaFifo(zgloszenie);
                    kolejka.add(i, zgloszenie);
                } else {
                    //Kolejka bez priorytetów
                    if(fifoOrLifo == 1) {
                        //Lifo
                        kolejka.addLast(zgloszenie);
                    } else {
                        //Fifo
                        kolejka.addFirst(zgloszenie);
                    }
                }
            }
        }

        // Szukanie miejsca w kolejce LIFO dla zgłoszenia
        private int szukajMiejscaLifo(Zgloszenie zgloszenie) {
            int i =0;
            while (i < kolejka.size() && kolejka.get(i).getPriorytet() <= zgloszenie.getPriorytet()) {
                i++;
            }
            return i;
        }

        // Szukanie miejsca w kolejce FIFO dla zgłoszenia
        private int szukajMiejscaFifo(Zgloszenie zgloszenie) {
            int i =0;
            while (i < kolejka.size() && kolejka.get(i).getPriorytet() < zgloszenie.getPriorytet()) {
                i++;
            }
            return i;
        }

        // Pobranie zgłoszenia z kolejki
        public Zgloszenie usun() {
            Zgloszenie zgl = (Zgloszenie) kolejka.removeFirst();
            return zgl;
        }

        // Pobranie zgłoszenia z kolejki
        public boolean usunWskazany(Zgloszenie zgl) {
            Boolean b = kolejka.remove(zgl);
            return b;
        }

        public void wypiszKolejke() {
            if(priorytetowaLubNiepriorytetowa == 1) {
                for (Zgloszenie z : kolejka) {
                    System.out.println("Nr: " + z.getTenNr() + " | Priorytet: " + z.getPriorytet());
                }
            } else {
                for (Zgloszenie z : kolejka) {
                    System.out.println("Nr: " + z.getTenNr());
                }
            }
        }

        public int getSize() {
            return this.kolejka.size();
        }

        public Zgloszenie usunPierwszy() {
            return kolejka.removeFirst();
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

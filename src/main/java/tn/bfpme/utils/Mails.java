package tn.bfpme.utils;

public class Mails {

    public static String generateAlternativeProposee(String employeeName, String startDate, String endDate, String managerName, String managerRole) {
        return String.format(
                "Cher/Chère %s,\n" +
                        "\n" +
                        "Je vous écris concernant votre demande de congé pour la période du %s au %s. Malheureusement, en raison des exigences actuelles de notre département, je ne suis pas en mesure d'approuver votre demande de congé pour les dates spécifiées.\n" +
                        "\n" +
                        "Cependant, je vous propose d'envisager de prendre vos congés à une autre date ultérieure. Je suis à votre disposition pour discuter de nouvelles dates qui conviendraient à la fois à vos besoins et aux exigences de notre département.\n" +
                        "\n" +
                        "Je vous remercie de votre compréhension et reste à votre disposition pour toute question.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "\n" +
                        "%s\n" +
                        "%s",
                employeeName, startDate, endDate, managerName, managerRole
        );
    }

    public static String generateEquiteEtEquilibre(String employeeName, String startDate, String endDate, String managerName, String managerRole) {
        return String.format(
                "Cher/Chère %s,\n" +
                        "\n" +
                        "Je vous informe que votre demande de congé pour la période du %s au %s ne peut pas être approuvée à ce moment. Plusieurs de vos collègues ont également demandé des congés pendant cette période, et afin de maintenir un équilibre et une équité au sein de l'équipe, je ne peux malheureusement pas autoriser votre demande.\n" +
                        "\n" +
                        "Je vous encourage à soumettre une nouvelle demande pour une période différente. N'hésitez pas à me consulter pour trouver un moment qui conviendrait à la fois à vos besoins et à ceux de l'équipe.\n" +
                        "\n" +
                        "Merci de votre compréhension.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "\n" +
                        "%s\n" +
                        "%s",
                employeeName, startDate, endDate, managerName, managerRole
        );
    }

    public static String generatePolitiqueDeRotationDesConges(String employeeName, String startDate, String endDate, String managerName, String managerRole) {
        return String.format(
                "Cher/Chère %s,\n" +
                        "\n" +
                        "Votre demande de congé pour la période du %s au %s ne peut être approuvée cette fois-ci. Conformément à notre politique de rotation des congés, nous devons nous assurer que chaque membre de l'équipe a la possibilité de prendre des congés pendant les périodes de vacances populaires.\n" +
                        "\n" +
                        "Je vous propose de replanifier vos congés à une autre période. Vous pouvez consulter le calendrier des congés disponibles et soumettre une nouvelle demande.\n" +
                        "\n" +
                        "Merci de votre compréhension et de votre coopération.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "\n" +
                        "%s\n" +
                        "%s",
                employeeName, startDate, endDate, managerName, managerRole
        );
    }

    public static String generateCongesCumulesNonAutorises(String employeeName, String startDate, String endDate, String managerName, String managerRole, int maxDays) {
        return String.format(
                "Cher/Chère %s,\n" +
                        "\n" +
                        "Je vous écris pour vous informer que votre demande de congé pour la période du %s au %s ne peut pas être approuvée. Selon la politique de notre entreprise, les congés cumulés au-delà de %d jours ne sont pas autorisés.\n" +
                        "\n" +
                        "Je vous invite à soumettre une nouvelle demande respectant cette limite. Si vous avez des questions concernant notre politique de congés, je suis disponible pour en discuter.\n" +
                        "\n" +
                        "Merci de votre compréhension.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "\n" +
                        "%s\n" +
                        "%s",
                employeeName, startDate, endDate, maxDays, managerName, managerRole
        );
    }

    public static String generateRemplacementNonDisponible(String employeeName, String startDate, String endDate, String managerName, String managerRole) {
        return String.format(
                "Cher/Chère %s,\n" +
                        "\n" +
                        "Je dois malheureusement refuser votre demande de congé pour la période du %s au %s. À l'heure actuelle, il n'y a pas de remplaçant disponible pour couvrir vos tâches pendant votre absence.\n" +
                        "\n" +
                        "Je vous encourage à proposer une autre période pour vos congés, et nous ferons de notre mieux pour organiser un remplacement adéquat.\n" +
                        "\n" +
                        "Merci de votre compréhension et de votre coopération.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "\n" +
                        "%s\n" +
                        "%s",
                employeeName, startDate, endDate, managerName, managerRole
        );
    }

    public static String generateEvaluationDePerformanceOuAudit(String employeeName, String startDate, String endDate, String managerName, String managerRole) {
        return String.format(
                "Cher/Chère %s,\n" +
                        "\n" +
                        "Je vous écris pour vous informer que votre demande de congé pour la période du %s au %s ne peut pas être approuvée. Pendant cette période, nous avons planifié une évaluation de performance/audit important(e) pour notre département, et votre présence est nécessaire.\n" +
                        "\n" +
                        "Je vous invite à soumettre une nouvelle demande de congé pour une période ultérieure. Je reste à votre disposition pour toute question ou pour discuter des dates alternatives possibles.\n" +
                        "\n" +
                        "Merci de votre compréhension.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "\n" +
                        "%s\n" +
                        "%s",
                employeeName, startDate, endDate, managerName, managerRole
        );
    }
}

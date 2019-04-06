package services;

import DTO.QuestionHashDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class SmartContractCallService {

//    String headerString =   "\"iwallet\\n\" +\n" +
//                            "\"--server localhost:30002\\n\" +\n" +
//                            "\"--account admin\\n\" +\n" +
//                            "\"call \\\"ContractBH6WzhwrhYBz5Hrg1gcR4X7Y3S8z23ShDMwB32zUHwXj\\\"\\n\" +";

//    StringBuilder sb = new StringBuilder().append(headerString);

//    public void createQuiz (Long id, String quizName, String sponsorName, Long prizePool, List<QuestionHashDTO> questionsList) throws IOException {
//
//
//
//        sb.append("\"createQuiz\" '[");
//
//        sb.append(id+",");
//        sb.append(quizName+",");
//        sb.append(sponsorName+",");
//        sb.append(prizePool+",");
//
//        String json = new Gson().toJson(questionsList);
//        sb.append(json);
//
//        sb.append("]'");
//
//        System.out.println(sb);
//
//        Runtime rt = Runtime.getRuntime();
//        Process pr = rt.exec(sb.toString());
//        sb.delete(0,sb.length());
//        pr.getInputStream();
//    }

//    public void recordCorrectAnswer (Long quizId, Long questionId,
//                                     String questionHash, String answerHash,
//                                     String publicKey) throws IOException {
//        sb.append("\"createQuiz\" '[");
//
//        sb.append(quizId+",");
//        sb.append(questionId+",");
//        sb.append(questionHash+",");
//        sb.append(answerHash+",");
//        sb.append(publicKey+",");
//
//        sb.append("]'");
//
//        System.out.println(sb);
//
//        Runtime rt = Runtime.getRuntime();
//        Process pr = rt.exec(sb.toString());
//        sb.delete(0,sb.length());
//        pr.getInputStream();
//    }
}

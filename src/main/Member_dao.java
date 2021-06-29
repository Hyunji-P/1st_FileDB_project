package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Member_dao {
	// 파일 경로
	String pathname1 = "D:\\1st_project_data\\File_DB.txt";

	// 파일 읽기, 쓰기
	BufferedReader br = null;
	BufferedWriter bw = null;

	public List<Member_bean> selectAll() {
		// 2021-06-07_박현지
		// File DB.txt 파일에 있는 모든 데이터를 읽어 들이는 메소드
		List<Member_bean> lists = new ArrayList<Member_bean>();
		Member_bean bean;

		try {
			br = new BufferedReader(new FileReader(new File(pathname1)));

			String imsi = null;

			// bean 클래스에 셋팅
			String[] arr = null;
			
			while ((imsi = br.readLine()) != null) {

				if (imsi.indexOf("아이디") == 0) {
					// 첫 줄이면 넘어감
					continue;
				} else {
					// 첫 줄이 아니면 bean에 셋팅
					bean = new Member_bean();
					arr = imsi.split(",");
					bean.setId(arr[0]);
					bean.setPassword(arr[1]);
					bean.setName(arr[2]);
					bean.setBirth(arr[3]);
					bean.setPhone(arr[4]);
					bean.setAge(Integer.parseInt(arr[5]));

					lists.add(bean);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	public int insertData(Member_bean bean) {
		// 2021-06-08_박현지
		// File DB에 insert 처리를 담당하는 메소드
		int cnt = 0;

		try {
			bw = new BufferedWriter(new FileWriter(new File(pathname1), true));

			// txt파일에 추가 될 DB
			String addDB = "";
			addDB += "\n" + bean.getId() + ",";
			addDB += bean.getPassword() + ",";
			addDB += bean.getName() + ",";
			addDB += bean.getBirth() + ",";
			addDB += bean.getPhone() + ",";
			addDB += bean.getAge();

			bw.write(addDB);

			cnt++;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	public int deleteData(String id) {
		// 2021_06_08_박현지
		// File DB에 Delete 처리를 담당하는 메소드
		int cnt = 0;

		try {
			String imsi = "";
			String result = ""; // 결과값
			br = new BufferedReader(new FileReader(new File(pathname1)));

			while ((imsi = br.readLine()) != null) {
				if (imsi.contains(id)) { // 삭제할 id가 있으면 넘어감
					continue;
				} else { // 삭제할 id가 없으면 순차적으로 표기
					result += imsi + "\n";
					cnt++;
				}

			}

			bw = new BufferedWriter(new FileWriter(new File(pathname1)));
			bw.write(result);
			bw.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int updateData(Member_bean bean) {
		// 2021-06-08_박현지
		// File DB에 update 처리를 담당하는 메소드
		int cnt = 0;

		try {
			String imsi = "";
			String result = ""; // 결과값
			br = new BufferedReader(new FileReader(new File(pathname1)));

			while ((imsi = br.readLine()) != null) {

				if (imsi.contains(bean.getId())) { // 수정할 id가 있으면 데이터 변경
					imsi = "";
					imsi += bean.getId() + ",";
					imsi += bean.getPassword() + ",";
					imsi += bean.getName() + ",";
					imsi += bean.getBirth() + ",";
					imsi += bean.getPhone() + ",";
					imsi += bean.getAge() + "\n";

					result += imsi;
					cnt++;

				} else { // 수정할 id가 없으면 기존 데이터 쓰기
					result += imsi + "\n";
				}

			}

			bw = new BufferedWriter(new FileWriter(new File(pathname1)));
			bw.write(result);
			bw.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

}
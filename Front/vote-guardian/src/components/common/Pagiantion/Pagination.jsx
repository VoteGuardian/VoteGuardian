'use clinet'
import { GrCaretPrevious } from "react-icons/gr";
import { GrCaretNext } from "react-icons/gr";
import './Pagination.scss';
import { useRecoilState, useRecoilValue } from "recoil";
import { votePage, voteTotalPage } from "@/recoil/atoms/createVoteListState";
import { useEffect } from "react";

export default function Pagination() {
    //현재 페이지 번호
    const [currentPage, setCurrentPage] = useRecoilState(votePage);
    //페이지를 몇 개 단위로 묶을지에 대한 수
    const limitPageCount = 5; 
    const totalPageCount = useRecoilValue(voteTotalPage);   

    //페이지를 5개(limitPageCount)씩 묶은 그룹 배열 생성하기
    const createPageGroupList = (totalPageCount, currentPage) => {
        //페이지 번호 전체(1부터 totalPageCount까지)를 담을 배열
        const totalPageGroup = Array(totalPageCount).fill(currentPage).map((x, y) => x + y);
        //5페이지(limitPageCount) 단위로 그룹 나누기
        const pageGroupList = [];
        for(let i = 0; i < totalPageGroup.length; i += limitPageCount) {
            pageGroupList.push(totalPageGroup.slice(i, i+limitPageCount));
        }
        return pageGroupList;
    }
    //페이지 그룹 생성
    let pageGroup = createPageGroupList(totalPageCount, 1);
    //페이지 그룹의 마지막 번호
    const pageLastGroup = pageGroup.length;
    
    //현재 페이지가 속한 그룹 구하기
    const getCurrentPageGroup = (currentPage, limitPageCount) => {
        return Math.ceil(currentPage/limitPageCount)-1;
    }
    
    //현재 페이지가 속한 그룹(페이지가 바뀌면 같이 바뀜)
    let currentGroup = getCurrentPageGroup(currentPage, limitPageCount);
    
    //페이지가 바뀔 때마다 실행
    useEffect(() => {
        //초기 페이지 버튼색 설정
        //페이지 버튼들을 하나의 배열로 만들기
        const buttonList = document.getElementsByClassName("page-button-list");
        const buttonListChild = buttonList[0].childNodes;
        //마지막 번호까지
        let lastButtonNumber = buttonListChild.length;
        //현재 버튼이 배열에서 몇 번째인지
        let currentButton = 0;
        //현재 보고 있는 페이지의 버튼은 클래스 이름에 clicked 추가
        if(currentPage % 5 === 0) {
            currentButton = currentPage-1;
            buttonListChild[currentButton].classList.add("clicked");
            buttonListChild[currentButton].classList.remove("none");
        } else {
            currentButton = (currentPage%5)-1;
            console.log(pageGroup);
            //buttonListChild[currentButton].classList.add("clicked");
            //buttonListChild[currentButton].classList.remove("none");
        }
        //현재 페이지를 제외한 번호들에서 clicked 제거(css 효과 제거를 위함)
        for(let i = 0; i < lastButtonNumber; i++) {
            if(i !== currentButton) {
                buttonListChild[i].classList.remove("clicked");
                buttonListChild[i].classList.add("none");
            }
        }
    }, []);
    
    //이전 버튼 클릭 시(페이지 그룹 이동)
    function handlePreviousPage() {
        //이동할 페이지는 이전 페이지 그룹의 마지막 숫자
        let previousPageGroup = currentGroup-1;
        setCurrentPage(pageGroup[previousPageGroup][4]); //현재 페이지 변경
        //현재 페이지가 속한 그룹도 변경됨
        currentGroup = getCurrentPageGroup(currentPage, limitPageCount);
    }

    //다음 버튼 클릭 시(페이지 그룹 이동)
    function handleNextPage() {
        //이동할 페이지는 다음 페이지 그룹의 첫 번째 숫자
        let nextPageGroup = currentGroup+1;
        setCurrentPage(pageGroup[nextPageGroup][0]); //현재 페이지 변경
        //현재 페이지가 속한 그룹도 변경됨
        currentGroup = getCurrentPageGroup(currentPage, limitPageCount);
    }

    //페이지 버튼 클릭 시 url 변경
    function handleChangePage(e) {
        //현재 페이지 변경
        setCurrentPage(e.target.innerText);
    }

    return (
        <div className="pagenation-wrap">
            {currentPage > 5 &&
                <div className="prev-button-wrap" onClick={handlePreviousPage}>
                    <GrCaretPrevious />
                </div>
            }
            <div className="page-button-list">
                {pageGroup[currentGroup] && pageGroup[currentGroup].map((n) => {
                    return(
                        <div className="page-button-wrap" onClick={handleChangePage}>
                            <p className="page-button">{n}</p>
                        </div>
                    )
                })}
            </div>
            {currentGroup !== pageLastGroup-1 &&
                <div className="next-button-wrap" onClick={handleNextPage}>
                    <GrCaretNext />
                </div>
            }
        </div>
    )
}